package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<ValidLogin, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() > 4;
    }

}
