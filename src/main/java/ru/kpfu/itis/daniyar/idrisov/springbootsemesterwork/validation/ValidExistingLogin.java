package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistingLoginValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExistingLogin {

    String message() default "existing login";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
