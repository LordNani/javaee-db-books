package main.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String isbn;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String title;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String author;

}