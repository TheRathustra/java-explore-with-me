package ru.practicum.mainService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainService.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
