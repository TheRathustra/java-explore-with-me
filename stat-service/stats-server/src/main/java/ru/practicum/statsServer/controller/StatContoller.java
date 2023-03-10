package ru.practicum.statsServer.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statsDto.dto.HitDto;
import ru.practicum.statsDto.dto.HitDtoAnswer;
import ru.practicum.statsServer.model.Hit;
import ru.practicum.statsServer.model.HitMapper;
import ru.practicum.statsServer.service.StatService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class StatContoller {

    private final StatService statService;

    @PostMapping(path = "/hit")
    public ResponseEntity<Object> addHit(@RequestBody HitDto hitDto) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.CREATED);
        statService.add(HitMapper.dtoToInctance(hitDto));
        return response;
    }

    @GetMapping(path = "/stats")
    public ResponseEntity<List<HitDtoAnswer>> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                    @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                    @RequestParam(name = "uris", defaultValue = "") String[] uris,
                                    @RequestParam(name = "unique", required = false, defaultValue = "false") boolean unique) {
        if (start.isAfter(LocalDateTime.now())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Hit> hits = statService.getStats(start, end, List.of(uris), unique);
        return new ResponseEntity<>(hits.stream().map(HitMapper::inctanceToHitDtoAnswer)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
