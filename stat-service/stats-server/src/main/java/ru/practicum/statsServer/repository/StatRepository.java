package ru.practicum.statsServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.statsServer.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Hit, Long> {

    @Query("SELECT h from Hit h where h.timestamp between :start and :end order by h.id")
    public List<Hit> getStatsBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    @Query("SELECT distinct h from Hit h where h.timestamp between :start and :end order by h.id")
    public List<Hit> getStatsBetweenDatesUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT h from Hit h where h.uri in :uris and h.timestamp between :start and :end order by h.id")
    public List<Hit> getStatsBetweenDatesUriInCollection(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("uris") List<String> uris);

    @Query("SELECT distinct h from Hit h where h.uri in :uris and h.timestamp between :start and :end order by h.id")
    public List<Hit> getStatsBetweenDatesUriInCollectionUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("uris") List<String> uris);

}
