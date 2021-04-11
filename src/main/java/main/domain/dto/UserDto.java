package main.domain.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "Username can't be empty")
    @Size(min = 4,max=25, message = "Username length must be between 4 and 25 characters")
    @Pattern(regexp="^[A-Za-z0-9]+$",message = "Username must contain only latin and numbers")
    private String username;

    @NotNull(message = "Password can't be empty")
    @Size(min = 8,max=20, message = "Password length must be between 8 and 20 characters")
    private String password;

}