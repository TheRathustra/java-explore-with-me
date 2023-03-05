package ru.practicum.mainService.service.impl.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.model.User;
import ru.practicum.mainService.repository.admins.UserRepositoryAdmin;
import ru.practicum.mainService.service.api.admins.UserServiceAdmin;

import java.util.List;

@Service
public class UserServiceAdminImpl implements UserServiceAdmin {

    private final UserRepositoryAdmin repository;

    @Autowired
    public UserServiceAdminImpl(UserRepositoryAdmin repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> get(Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from, size);
        Page<User> users = repository.findAll(pageRequest);
        return users.toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
