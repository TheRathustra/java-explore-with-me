package ru.practicum.mainService.validator;

import org.springframework.stereotype.Component;
import ru.practicum.mainService.dto.user.UserDto;
import ru.practicum.mainService.error.exception.user.UserValidationException;

@Component
public class UserValidator {

    public void validateUser(UserDto user) {
        if (user.getName().isEmpty() || user.getName().isBlank()) {
            throw new UserValidationException("Field: name. Error: must not be blank. Value: null");
        }

        if (user.getEmail().isEmpty() || user.getEmail().isBlank()) {
            throw new UserValidationException("Field: email. Error: must not be blank. Value: null");
        }
    }

}
