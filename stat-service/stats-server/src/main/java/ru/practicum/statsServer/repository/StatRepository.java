package ru.practicum.statsServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.statsDto.dto.HitDtoAnswer;
import ru.practicum.statsServer.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Hit, Long> {

    @Query("SELECT h.app, h.uri, count(*) as hits from Hit h " +
            "where h.timestamp between :start and :end " +
            "group by h.app, h.uri " +
            "order by hits asc")
    List<HitDtoAnswer> getStatsBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT distinct (h.uri) as uri, h.app, count(*) as hits from Hit h " +
            "where h.timestamp between :start and :end " +
            "GROUP BY h.app, h.uri " +
            "order by hits asc")
    List<HitDtoAnswer> getStatsBetweenDatesUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT h.app, h.uri, COUNT(*) as hits from Hit h " +
            "where h.uri in :uris and h.timestamp between :start and :end " +
            "group by h.app, h.uri " +
            "order by hits asc")
    List<HitDtoAnswer> getStatsBetweenDatesUriInCollection(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("uris") List<String> uris);

    @Query("SELECT distinct (h.uri) as uri, h.app, count(*) as hits from Hit h " +
            "where h.uri in :uris and h.timestamp between :start and :end " +
            "GROUP BY h.app, h.uri " +
            "order by hits asc ")
    List<HitDtoAnswer> getStatsBetweenDatesUriInCollectionUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("uris") List<String> uris);

}
