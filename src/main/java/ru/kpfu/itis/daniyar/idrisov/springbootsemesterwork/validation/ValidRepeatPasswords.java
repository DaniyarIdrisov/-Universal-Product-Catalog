package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RepeatPasswordsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRepeatPasswords {

    String message() default "invalid passwords";

    String password();
    String repeatPassword();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        ValidRepeatPasswords[] value();
    }

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
