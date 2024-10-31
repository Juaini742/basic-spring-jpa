package com.core.jpatraining2.interfaces;


import com.core.jpatraining2.validation.DateComparisonValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateComparisonValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {

        String message() default "Finish date must be after start date";

        Class<?>[] group() default {};

        Class<? extends Payload>[] payload() default {};
}
