package ru.practicum.statsServer.service;

import ru.practicum.statsDto.dto.HitDtoAnswer;
import ru.practicum.statsServer.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {

    Hit add(Hit hit);

    List<HitDtoAnswer> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);

}
