package com.spring.assignment.employeesystem.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UserUniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)

public @interface Unique {
    String message() default "already exist";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
