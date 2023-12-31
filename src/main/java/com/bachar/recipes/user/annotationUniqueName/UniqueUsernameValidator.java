package com.bachar.recipes.user.annotationUniqueName;

import com.bachar.recipes.user.User;
import com.bachar.recipes.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        User userInDB = userRepository.findByUsername(value);
        if (userInDB == null) {
            return true;
        }
        return false;
    }
}
