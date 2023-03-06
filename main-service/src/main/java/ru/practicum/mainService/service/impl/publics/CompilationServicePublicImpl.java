package ru.practicum.mainService.service.impl.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.compilation.CompilationDto;
import ru.practicum.mainService.dto.compilation.CompilationMapper;
import ru.practicum.mainService.error.exception.compilation.CompilationNotFountException;
import ru.practicum.mainService.model.Compilation;
import ru.practicum.mainService.repository.publics.CompilationRepositoryPublic;
import ru.practicum.mainService.service.api.publics.CompilationServicePublic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompilationServicePublicImpl implements CompilationServicePublic {

    private final CompilationRepositoryPublic repository;

    @Autowired
    public CompilationServicePublicImpl(CompilationRepositoryPublic repository) {
        this.repository = repository;
    }

    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size) {
        int page = from / size;
        Pageable pageRequest = PageRequest.of(page, size);
        List<Compilation> compilations;
        if (pinned != null) {
            compilations = repository.findAllByPinned(pinned, pageRequest);
        } else {
            compilations = repository.findAll(pageRequest).toList();
        }

        return compilations.stream()
                .map(CompilationMapper::entityToCompilationDto).collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilationsById(Long compId) {
        Optional<Compilation> compilation = repository.findById(compId);
        if (compilation.isEmpty()) {
            throw new CompilationNotFountException("Compilation with id=" + compId + " was not found");
        }

        return CompilationMapper.entityToCompilationDto(compilation.get());
    }
}
