package main.controller;

import lombok.RequiredArgsConstructor;
import main.controller.exceptions.EmptySearchParamsException;
import main.controller.exceptions.NotAuthenticatedException;
import main.domain.dto.BookFindDto;
import main.domain.dto.UserDto;
import main.domain.entities.BookEntity;
import main.repositories.BookRepository;
import main.repositories.PermissionRepository;
import main.repositories.UserRepository;
import main.service.UserBookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    private final UserBookService userService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }


    @GetMapping("/catalog")
    public String catalog(Model model)
    {
        model.addAttribute("books", bookRepository.findAll());
        return "catalog";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<BookEntity> books = userService.getUserFavorites(userDetails.getUsername());
        model.addAttribute("books", books);
        return "profile";
    }


    @PostMapping(value="/books/save",consumes="application/json",headers = "content-type=application/x-www-form-urlencoded;charset=UTF-8")
    public ModelAndView  addBookToFavorites(@Valid BookFindDto bookDto, Authentication authentication, ModelMap model) {
        if(authentication instanceof AnonymousAuthenticationToken)
            throw new NotAuthenticatedException();

        if(isEmpty(bookDto.getIsbn()) && isEmpty(bookDto.getTitle()))
            throw new EmptySearchParamsException();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String result = userService.saveBook(userDetails, bookDto);
        model.addAttribute("popup", result);
        return new ModelAndView("redirect:/profile", model);
    }

    @PostMapping(value="/books/remove",consumes="application/json",headers = "content-type=application/x-www-form-urlencoded;charset=UTF-8")
    public ModelAndView  removeBookFromFavorties(@Valid BookFindDto bookDto, Authentication authentication, ModelMap model) {

        if(isEmpty(bookDto.getIsbn()) && isEmpty(bookDto.getTitle()))
            throw new EmptySearchParamsException();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String result = userService.unsaveBook(userDetails, bookDto);
        model.addAttribute("popup", result);
        return new ModelAndView("redirect:/profile", model);
    }

    @PostMapping(value="/signup",consumes="application/json",headers = "content-type=application/x-www-form-urlencoded;charset=UTF-8")
    public ResponseEntity<String> postSignup(@Valid UserDto userDto) {
        if(userRepository.existsByLogin(userDto.getUsername())){
            return new ResponseEntity<>("This Username is already taken", HttpStatus.BAD_REQUEST);
        }


        userService.registerUser(userDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        headers.add("Result", "Successful signup!");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }


}
