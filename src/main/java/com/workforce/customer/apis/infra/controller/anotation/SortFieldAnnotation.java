package com.workforce.customer.apis.infra.controller.anotation;

import com.workforce.customer.apis.infra.controller.validator.SortFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SortFieldValidator.class)
public @interface SortFieldAnnotation {

    public String message() default "Invalid sort field.";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
