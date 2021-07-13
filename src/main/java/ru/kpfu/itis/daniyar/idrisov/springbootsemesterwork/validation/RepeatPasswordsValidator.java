package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RepeatPasswordsValidator implements ConstraintValidator<ValidRepeatPasswords, Object> {

    private String passwordPropertyName;
    private String repeatPasswordPropertyName;

    @Override
    public void initialize(ValidRepeatPasswords constraintAnnotation) {
        this.passwordPropertyName = constraintAnnotation.password();
        this.repeatPasswordPropertyName = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object password = new BeanWrapperImpl(o).getPropertyValue(passwordPropertyName);
        Object repeatPassword = new BeanWrapperImpl(o).getPropertyValue(repeatPasswordPropertyName);

        return password.toString().equals(repeatPassword.toString());
    }

}
