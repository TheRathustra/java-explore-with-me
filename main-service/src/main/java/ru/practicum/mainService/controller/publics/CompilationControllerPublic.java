package ru.practicum.mainService.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.compilation.CompilationDto;
import ru.practicum.mainService.service.api.publics.CompilationServicePublic;

import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
public class CompilationControllerPublic {

    private final CompilationServicePublic compilationService;

    @Autowired
    public CompilationControllerPublic(CompilationServicePublic compilationService) {
        this.compilationService = compilationService;
    }

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(name = "pinned", required = false) Boolean pinned,
                                                @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return compilationService.getCompilations(pinned, from, size);
    }

    @GetMapping(path = "/{compId}")
    public ResponseEntity<CompilationDto> getCompilationsById(@PathVariable(name = "compId") Long compId) {
        CompilationDto compilationDto = compilationService.getCompilationsById(compId);
        ResponseEntity<CompilationDto> response = new ResponseEntity<>(compilationDto, HttpStatus.OK);
        return response;
    }

}
