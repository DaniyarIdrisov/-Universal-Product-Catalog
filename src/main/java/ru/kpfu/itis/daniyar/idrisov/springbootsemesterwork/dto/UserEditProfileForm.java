package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.validation.ValidPassword;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.validation.ValidRepeatPasswords;

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
public class UserEditProfileForm {

    private String firstName;

    private String lastName;

    @ValidPassword(message = "{errors.invalid.password}")
    private String password;

    private String repeatPassword;

}
