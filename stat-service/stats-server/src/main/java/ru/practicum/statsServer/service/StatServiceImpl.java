package ru.practicum.statsServer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.statsServer.model.Hit;
import ru.practicum.statsServer.model.HitMapper;
import ru.practicum.statsServer.repository.StatRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository repository;

    @Override
    public Hit add(Hit hit) {
        return repository.save(hit);
    }

    @Override
    public List<Hit> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        List<Hit> hits;
        if (unique) {
            if (uris.isEmpty()) {
                hits = repository.getStatsBetweenDatesUnique(start, end)
                        .stream().map(HitMapper::hitAnswerToInctance).collect(Collectors.toList());
            } else {
                hits = repository.getStatsBetweenDatesUriInCollectionUnique(start, end, uris)
                        .stream().map(HitMapper::hitAnswerToInctance).collect(Collectors.toList());
            }
        } else {
            if (uris.isEmpty()) {
                hits = repository.getStatsBetweenDates(start, end)
                        .stream().map(HitMapper::hitAnswerToInctance).collect(Collectors.toList());
            } else {
                hits = repository.getStatsBetweenDatesUriInCollection(start, end, uris)
                        .stream().map(HitMapper::hitAnswerToInctance).collect(Collectors.toList());
            }
        }
        return hits;
    }
}
