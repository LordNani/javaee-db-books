package main.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String username;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String password;

}