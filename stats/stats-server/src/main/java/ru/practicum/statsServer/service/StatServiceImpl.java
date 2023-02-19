package ru.practicum.statsServer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.statsServer.model.Hit;
import ru.practicum.statsServer.repository.StatRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository repository;

    @Override
    public Hit add(Hit hit) {
        return repository.save(hit);
    }

    @Override
    public List<Hit> getStats() {
        return repository.findAll();
    }
}
