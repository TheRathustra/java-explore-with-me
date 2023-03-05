package ru.practicum.mainService.dto.compilation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class NewCompilationDto {

    private Set<Integer> events;

    private Boolean pinned;

    private String title;

}
