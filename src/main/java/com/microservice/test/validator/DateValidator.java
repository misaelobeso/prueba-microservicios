package com.microservice.test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateValidator implements ConstraintValidator<IsDate, String> {
    private DateTimeFormatter dateFormatter;

    @Override
    public void initialize(@NotNull IsDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.dateFormatter = DateTimeFormatter.ofPattern(constraintAnnotation.value())
                .withResolverStyle(ResolverStyle.STRICT);
    }

    @Override
    public boolean isValid(String dateValue, ConstraintValidatorContext context)
            throws IllegalArgumentException {
        Boolean result = Boolean.TRUE;

        try {
            this.dateFormatter.parse(dateValue);
        } catch (DateTimeParseException e) {
            result = Boolean.FALSE;
        }

        return result.booleanValue();
    }
}