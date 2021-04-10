package main.service;

import lombok.RequiredArgsConstructor;
import main.controller.exceptions.BookNotExistsException;
import main.domain.dto.BookFindDto;
import main.domain.dto.UserDto;
import main.domain.entities.BookEntity;
import main.domain.entities.UserBookEntity;
import main.domain.entities.UserEntity;
import main.domain.type.Permission;
import main.repositories.BookRepository;
import main.repositories.PermissionRepository;
import main.repositories.UserBookRepository;
import main.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBookService {
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final BookRepository bookRepository;
    private final UserBookRepository userBookRepository;
    public void registerUser(UserDto userDto){
        userRepository.save(UserEntity.builder()
                .login(userDto.getUsername())
                .password(userDto.getPassword())
                .permissions(permissionRepository.findByPermission(Permission.USER))
                .build());

    }

    public String saveBook(UserDetails userDetails, BookFindDto bookFindDto) {
        String isbn = bookFindDto.getIsbn();
        String title = bookFindDto.getTitle();
        List<BookEntity> bookList;
        log.debug("BookFindDTO: Isbn {}, Title {}",bookFindDto.getIsbn(),bookFindDto.getTitle());
        if(!isEmpty(bookFindDto.getIsbn())){
            if(!bookRepository.existsByIsbn(isbn))
                throw new BookNotExistsException();
            bookList  = bookRepository.findByIsbn(isbn);
        }
        else if (!isEmpty(bookFindDto.getTitle())){
            if(!bookRepository.existsByTitle(title))
                throw new BookNotExistsException();
            bookList  = bookRepository.findBookEntityByTitleContains(title);
        }
        else {
            throw new BookNotExistsException();
        }

        Optional<UserEntity> userEntityOptional = userRepository.findByLogin(userDetails.getUsername());

        BookEntity book = bookList.get(0);
        UserEntity user = userEntityOptional.get();

        if(userBookRepository.existsByUserAndBook(user,book))
            return "Already in your favorites!";

        UserBookEntity userBookEntity = UserBookEntity.builder().book(book).user(user).build();
        log.debug("User {} saved book {}",user.toString(),book.toString());
        userBookRepository.save(userBookEntity);
        return "Book saved successfully";

    }

    public List<BookEntity> getUserFavorites(String username) {
        List<BookEntity> favorites = new ArrayList<>();
        List<UserBookEntity> userBooks = userBookRepository.findByUser_LoginContains(username);
        for(UserBookEntity userBookEntity: userBooks){
            favorites.add(userBookEntity.getBook());
        }
        return favorites;
    }

    public String unsaveBook(UserDetails userDetails, BookFindDto bookFindDto) {
        String isbn = bookFindDto.getIsbn();
        String title = bookFindDto.getTitle();

        List<BookEntity> bookList;
        log.debug("BookFindDTO: Isbn {}, Title {}",bookFindDto.getIsbn(),bookFindDto.getTitle());
        if(!isEmpty(bookFindDto.getIsbn())){
            if(!bookRepository.existsByIsbn(isbn))
                throw new BookNotExistsException();
            bookList  = bookRepository.findByIsbn(isbn);
        }
        else if (!isEmpty(bookFindDto.getTitle())){
            if(!bookRepository.existsByTitle(title))
                throw new BookNotExistsException();
            bookList  = bookRepository.findBookEntityByTitleContains(title);
        }
        else {
            throw new BookNotExistsException();
        }

        Optional<UserEntity> userEntityOptional = userRepository.findByLogin(userDetails.getUsername());

        BookEntity book = bookList.get(0);
        UserEntity user = userEntityOptional.get();
        log.debug("Book is {}, user is {}",book,user);
        if(!userBookRepository.existsByUserAndBook(user,book))
            return "No such book in favorites";

        log.debug("User {} saved book {}",user.toString(),book.toString());
        userBookRepository.delete(userBookRepository.findByUserAndBook(user,book).get(0));

        return "Book removed successfully";
    }
}
