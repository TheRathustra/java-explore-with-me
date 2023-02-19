package ru.practicum.statsServer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statsServer.dto.HitDto;
import ru.practicum.statsServer.model.Hit;
import ru.practicum.statsServer.service.StatService;

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
    public List<Hit> getStats(@RequestParam(name = "start", required = false) String start,
                              @RequestParam(name = "end", required = false) String end,
                              @RequestParam(name = "uris", required = false) String[] uris,
                              @RequestParam(name = "unique", required = false, defaultValue = "false") boolean unique) {
        return statService.getStats();
    }

}
