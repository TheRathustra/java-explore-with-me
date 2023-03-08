package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.user.UserDto;
import ru.practicum.mainService.dto.user.UserMapper;
import ru.practicum.mainService.model.User;
import ru.practicum.mainService.service.api.admins.UserServiceAdmin;
import ru.practicum.mainService.validator.UserValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/users")
public class UserControllerAdmin {

    private final UserServiceAdmin userService;

    private final UserValidator validator;

    @Autowired
    public UserControllerAdmin(UserServiceAdmin userService, UserValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(name = "ids") List<Long> ids,
                                  @RequestParam(name = "from", defaultValue = "0") Integer from,
                                  @RequestParam(name = "size", defaultValue = "10") Integer size) {
        /*
        Возвращает информацию обо всех пользователях (учитываются параметры ограничения выборки),
        либо о конкретных (учитываются указанные идентификаторы)
        В случае, если по заданным фильтрам не найдено ни одного пользователя, возвращает пустой список
         */
        return userService.getUsers(ids, from, size)
                .stream().map(UserMapper::entityToUserDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        validator.validateUser(userDto);
        User user = UserMapper.userDtoToEntity(userDto);
        User newUser = userService.create(user);
        UserDto newUserDto = UserMapper.entityToUserDto(newUser);
        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{userId}")
    @Transactional
    public void delete(@PathVariable(name = "userId") Long id) {
        userService.delete(id);
    }

}
