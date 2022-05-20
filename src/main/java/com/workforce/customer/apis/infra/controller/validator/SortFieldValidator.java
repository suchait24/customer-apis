package com.workforce.customer.apis.infra.controller.validator;

import com.workforce.customer.apis.infra.controller.anotation.SortFieldAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SortFieldValidator implements ConstraintValidator<SortFieldAnnotation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
