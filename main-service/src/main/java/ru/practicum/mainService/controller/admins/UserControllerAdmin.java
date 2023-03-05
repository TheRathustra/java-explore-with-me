package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.user.UserDto;
import ru.practicum.mainService.dto.user.UserMapper;
import ru.practicum.mainService.model.User;
import ru.practicum.mainService.service.api.admins.UserServiceAdmin;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class UserControllerAdmin {

    private final UserServiceAdmin userService;

    @Autowired
    public UserControllerAdmin(UserServiceAdmin userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(name = "ids") List<Long> ids,
                                  @RequestParam(name = "from", defaultValue = "0") Integer from,
                                  @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return null;
    }

    @PostMapping
    @Transactional
    public User registerUser(@Valid @RequestBody UserDto userDto) {
        User user = UserMapper.userDtoToEntity(userDto);
        return userService.create(user);
    }

    @DeleteMapping(path = "/{userId}")
    @Transactional
    public void delete(@PathVariable(name = "userId") Long id) {
        userService.delete(id);
    }

}
