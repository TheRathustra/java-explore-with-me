package ru.practicum.statsServer.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statsDto.dto.HitDto;
import ru.practicum.statsServer.model.Hit;
import ru.practicum.statsServer.service.StatService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class StatContoller {

    private final StatService statService;

    @PostMapping(path = "/hit")
    public ResponseEntity<Object> addHit(@RequestBody HitDto hitDto) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatusCode.valueOf(201));
        statService.add(HitDto.dtoToInctance(hitDto));
        return response;
    }

    @GetMapping(path = "/stats")
    public List<Hit> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                              @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                              @RequestParam(name = "uris", required = false) String[] uris,
                              @RequestParam(name = "unique", required = false, defaultValue = "false") boolean unique) {
        List<String> urisList = uris == null ? Collections.emptyList() : List.of(uris);
        return statService.getStats(start, end, urisList, unique);
    }

}
