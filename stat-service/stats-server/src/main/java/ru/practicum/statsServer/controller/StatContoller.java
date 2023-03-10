package ru.practicum.statsServer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statsDto.dto.HitDto;
import ru.practicum.statsDto.dto.HitDtoAnswer;
import ru.practicum.statsServer.model.Hit;
import ru.practicum.statsServer.model.HitMapper;
import ru.practicum.statsServer.service.StatService;
import ru.practicum.statsServer.validator.HitValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class StatContoller {

    private final StatService statService;

    private final HitValidator validator;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT);

    @Autowired
    public StatContoller(StatService statService, HitValidator validator) {
        this.statService = statService;
        this.validator = validator;
    }

    @PostMapping(path = "/hit")
    public ResponseEntity<Hit> addHit(@RequestBody HitDto hitDto) {
        validator.validateHit(hitDto);
        Hit hit = statService.add(HitMapper.dtoToInctance(hitDto));
        return new ResponseEntity<>(hit, HttpStatus.CREATED);
    }

    @GetMapping(path = "/stats")
    public ResponseEntity<List<HitDtoAnswer>> getStats(@RequestParam(name = "start") String startDate,
                                    @RequestParam(name = "end") String endDate,
                                    @RequestParam(name = "uris", defaultValue = "") List<String> uris,
                                    @RequestParam(name = "unique", required = false, defaultValue = "false") Boolean unique) {

        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        log.info("get stats from = {} till = {} about uris = {} with unique ids = {}",
                startDate, endDate, uris, unique);

        List<Hit> hits = statService.getStats(start, end, uris, unique);
        return new ResponseEntity<>(hits.stream().map(HitMapper::inctanceToHitDtoAnswer)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
