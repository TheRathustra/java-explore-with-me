package ru.practicum.mainService.service.impl.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.error.exception.user.UserNotFoundException;
import ru.practicum.mainService.model.User;
import ru.practicum.mainService.repository.admins.UserRepositoryAdmin;
import ru.practicum.mainService.service.api.admins.UserServiceAdmin;

import java.util.List;
import java.util.Optional;

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
    public List<User> getUsers(List<Long> ids, Integer from, Integer size) {
        int page = from / size;
        Pageable pageRequest = PageRequest.of(page, size);
        List<User> users;
        if (ids != null) {
            users = repository.findAllByIdIn(ids, pageRequest);
        } else {
            users = repository.findAll(pageRequest).toList();
        }
        return users;
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id=" + id + " was not found");
        }

        repository.deleteById(id);
    }
}
