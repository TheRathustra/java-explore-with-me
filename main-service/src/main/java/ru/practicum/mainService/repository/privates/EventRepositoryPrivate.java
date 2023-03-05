package ru.practicum.mainService.repository.privates;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainService.model.Event;

public interface EventRepositoryPrivate extends JpaRepository<Event, Long> {



}
