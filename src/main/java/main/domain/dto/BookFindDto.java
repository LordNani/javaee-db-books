package main.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFindDto {

    @Getter
    @Setter
    private String isbn;

    @Getter
    @Setter
    private String title;

}