package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.UsersService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingLoginValidator implements ConstraintValidator<ValidExistingLogin, String> {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        UserDto userDto = usersService.getUserByLogin(s);
        if (userDto == null) {
            return true;
        }
        else {
            return false;
        }
    }

}
