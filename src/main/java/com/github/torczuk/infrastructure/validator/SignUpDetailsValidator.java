package com.github.torczuk.infrastructure.validator;

import com.github.torczuk.dto.SignUpDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SignUpDetailsValidator implements Validator {
    private static final int MINIMUM_PASSWORD_LENGTH = 4;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override public boolean supports(Class<?> clazz) {
        return SignUpDetails.class.isAssignableFrom(clazz);
    }

    @Override public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required", "First name can not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required", "Last name can not be empty");

        SignUpDetails signUpDetails = (SignUpDetails) target;
        if(signUpDetails.getPassword() != null && signUpDetails.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH ){
            errors.rejectValue("password", "field.min.length", "Password is to short");
        }

        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = emailPattern.matcher(signUpDetails.getEmail());
        if(!matcher.matches()) {
            errors.rejectValue("email", "field.invalid.email","Email is invalid.");
        }
    }
}
