package ru.practicum.mainService.service.impl.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.compilation.CompilationDto;
import ru.practicum.mainService.dto.compilation.CompilationMapper;
import ru.practicum.mainService.dto.compilation.NewCompilationDto;
import ru.practicum.mainService.dto.compilation.UpdateCompilationRequest;
import ru.practicum.mainService.error.exception.compilation.CompilationNotFountException;
import ru.practicum.mainService.model.Compilation;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.repository.admins.CompilationRepositoryAdmin;
import ru.practicum.mainService.repository.admins.EventRepositoryAdmin;
import ru.practicum.mainService.service.api.admins.CompilationServiceAdmin;

import java.util.List;
import java.util.Optional;

@Service
public class CompilationServiceAdminImpl implements CompilationServiceAdmin {

    private final CompilationRepositoryAdmin repository;

    private final EventRepositoryAdmin eventRepository;

    @Autowired
    public CompilationServiceAdminImpl(CompilationRepositoryAdmin repository, EventRepositoryAdmin eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    @Override
    public CompilationDto saveCompilation(NewCompilationDto newCompilationDto) {
        Compilation compilation = CompilationMapper.newCompilationDtoToEntity(newCompilationDto);
        List<Event> events = eventRepository.findAllByIdIn(newCompilationDto.getEvents());
        compilation.setEvents(events);
        Compilation newCompilation = repository.save(compilation);

        return CompilationMapper.entityToCompilationDto(newCompilation);
    }

    @Override
    public void deleteCompilation(Long compId) {

        Optional<Compilation> compilationOptional = repository.findById(compId);
        if (compilationOptional.isEmpty()) {
            throw new CompilationNotFountException("Compilation with id=" + compId + " was not found");
        }

        repository.delete(compilationOptional.get());

    }

    @Override
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {

        Optional<Compilation> compilationOptional = repository.findById(compId);
        if (compilationOptional.isEmpty()) {
            throw new CompilationNotFountException("Compilation with id=" + compId + " was not found");
        }

        Compilation compilation = compilationOptional.get();

        if (updateCompilationRequest.getEvents() != null) {
            List<Event> events = eventRepository.findAllByIdIn(updateCompilationRequest.getEvents());
            compilation.setEvents(events);
        }

        if (updateCompilationRequest.getTitle() != null)
            compilation.setTitle(updateCompilationRequest.getTitle());

        if (updateCompilationRequest.getPinned() != null)
            compilation.setPinned(updateCompilationRequest.getPinned());

        Compilation updatedCompilation = repository.save(compilation);

        return CompilationMapper.entityToCompilationDto(updatedCompilation);
    }
}
