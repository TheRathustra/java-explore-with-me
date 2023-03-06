package ru.practicum.mainService.repository.publics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.mainService.model.Event;

@Repository
public interface EventRepositoryPublic extends JpaRepository<Event, Long> {
}
