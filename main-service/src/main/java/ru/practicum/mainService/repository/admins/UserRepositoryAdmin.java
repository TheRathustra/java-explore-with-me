package ru.practicum.mainService.repository.admins;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainService.model.User;

public interface UserRepositoryAdmin extends JpaRepository<User, Long> {
}
