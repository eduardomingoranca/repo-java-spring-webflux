package br.com.caesar.webfluxcourse.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrimStringValidator implements ConstraintValidator<TrimString, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || s.trim().length() == s.length();
    }

}
