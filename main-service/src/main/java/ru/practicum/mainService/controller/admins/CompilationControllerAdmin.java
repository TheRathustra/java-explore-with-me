package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.compilation.CompilationDto;
import ru.practicum.mainService.dto.compilation.NewCompilationDto;
import ru.practicum.mainService.dto.compilation.UpdateCompilationRequest;
import ru.practicum.mainService.service.api.admins.CompilationServiceAdmin;
import ru.practicum.mainService.validator.CompilationValidator;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path = "/admin/compilations")
public class CompilationControllerAdmin {

    private final CompilationServiceAdmin compilationService;

    private final CompilationValidator validator;

    @Autowired
    public CompilationControllerAdmin(CompilationServiceAdmin compilationService, CompilationValidator validator) {
        this.compilationService = compilationService;
        this.validator = validator;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompilationDto> saveCompilation(@RequestBody NewCompilationDto newCompilationDto) {
        validator.validateCompilation(newCompilationDto);
        CompilationDto compilationDto = compilationService.saveCompilation(newCompilationDto);
        return new ResponseEntity<>(compilationDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{compId}")
    @Transactional
    public ResponseEntity<Object> deleteCompilation(@PathVariable(name = "compId") Long compId) {
        compilationService.deleteCompilation(compId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/{compId}")
    @Transactional
    public CompilationDto updateCompilation(@PathVariable(name = "compId") Long compId,
                                            @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return compilationService.updateCompilation(compId, updateCompilationRequest);
    }

}
