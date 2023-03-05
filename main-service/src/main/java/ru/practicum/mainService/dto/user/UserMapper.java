package ru.practicum.mainService.dto.user;

import ru.practicum.mainService.model.User;

public class UserMapper {

    public static User userDtoToEntity(UserDto dto) {
        return new User()
                .setName(dto.getName())
                .setEmail(dto.getEmail());
    }

    public static UserDto entityToUserDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public static UserShortDto entityToUserShortDto(User entity) {
        UserShortDto dto = new UserShortDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

}
