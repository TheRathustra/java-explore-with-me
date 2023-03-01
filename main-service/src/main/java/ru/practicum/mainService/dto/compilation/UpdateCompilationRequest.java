package ru.practicum.mainService.dto.compilation;

import java.util.Set;

public class UpdateCompilationRequest {

    private Set<Long> events;

    private Boolean pinned;

    private String title;

}
