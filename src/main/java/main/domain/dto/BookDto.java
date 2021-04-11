package main.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$")
    private String isbn;

    @NotNull(message = "Book title cannot be empty")
    @NotEmpty

    private String title;

    @NotNull(message = "Book author cannot be empty")
    @NotEmpty

    private String author;

}