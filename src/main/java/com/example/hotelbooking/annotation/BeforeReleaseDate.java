package com.example.hotelbooking.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Past;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = BeforeReleaseDateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Past
public @interface BeforeReleaseDate {

    String message() default "Date must not be before {value}";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};

    String value() default "2024-04-07";
}
