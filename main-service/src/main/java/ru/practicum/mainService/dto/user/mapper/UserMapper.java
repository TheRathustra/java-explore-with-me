package ru.practicum.mainService.dto.user.mapper;

import ru.practicum.mainService.dto.user.UserDto;
import ru.practicum.mainService.model.User;

public class UserMapper {

    public static User userDtoToInctance(UserDto dto) {
        return new User()
                .setName(dto.getName())
                .setEmail(dto.getEmail());
    }

}
