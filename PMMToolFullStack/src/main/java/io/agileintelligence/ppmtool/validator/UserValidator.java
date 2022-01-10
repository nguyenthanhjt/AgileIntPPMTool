package io.agileintelligence.ppmtool.validator;

import io.agileintelligence.ppmtool.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (6 > user.getPassword().length()) {
            errors.rejectValue("password", "Length", "Password must be at least 6 characters");

        }

        // confirmationPassword
        if (!user.getPassword().equals(user.getConfirmationPassword())) {
            errors.rejectValue("confirmationPassword", "Match", "Password must be matched");
        }

    }
}
