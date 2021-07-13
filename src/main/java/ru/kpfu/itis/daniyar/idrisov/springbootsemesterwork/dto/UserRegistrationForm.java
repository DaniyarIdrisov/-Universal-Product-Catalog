package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.validation.*;

import javax.validation.constraints.Email;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ValidRepeatPasswords(
        message = "{errors.invalid.repeat_passwords}",
        password = "password",
        repeatPassword = "repeatPassword"
)
public class UserRegistrationForm {

    @ValidExistingLogin(message = "{errors.existing.login}")
    @ValidLogin(message = "{errors.invalid.login}")
    private String login;

    @ValidPassword(message = "{errors.invalid.password}")
    private String password;

    private String repeatPassword;

    private String firstName;

    private String lastName;

    @Email(message = "{errors.incorrect.email}")
    private String email;

}