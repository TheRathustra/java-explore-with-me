package ru.practicum.mainService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.user.UserDto;
import ru.practicum.mainService.dto.user.mapper.UserMapper;
import ru.practicum.mainService.model.User;
import ru.practicum.mainService.service.api.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> get(@RequestParam(name = "from", defaultValue = "0") Integer from, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return userService.get(from, size);
    }

    @PostMapping
    @Transactional
    public User create(@Valid @RequestBody UserDto userDto) {
        User user = UserMapper.userDtoToInctance(userDto);
        return userService.create(user);
    }

    @DeleteMapping
    @Transactional
    public void delete(Long id) {
        userService.delete(id);
    }

}
