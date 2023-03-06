package ru.practicum.mainService.repository.admins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.mainService.model.Event;

import java.util.List;

@Repository
public interface EventRepositoryAdmin extends JpaRepository<Event, Long> {

    List<Event> findAllByCategoryId(Long categoryId);

}
