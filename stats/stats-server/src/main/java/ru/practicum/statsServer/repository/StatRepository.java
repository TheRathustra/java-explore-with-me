package ru.practicum.statsServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.statsServer.model.Hit;

@Repository
public interface StatRepository extends JpaRepository<Hit, Long> {

}
