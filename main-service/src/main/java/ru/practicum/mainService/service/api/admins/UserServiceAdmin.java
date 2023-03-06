package ru.practicum.mainService.service.api.admins;

import ru.practicum.mainService.model.User;

import java.util.List;

public interface UserServiceAdmin {

    User create(User user);

    List<User> getUsers(List<Long> ids, Integer from, Integer size);

    void delete(Long id);

}
