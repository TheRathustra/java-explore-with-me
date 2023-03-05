package ru.practicum.mainService.dto.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.mainService.model.Location;
import ru.practicum.mainService.model.State;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class UpdateEventAdminRequest {

    private Long id;

    @Size(min = 20, max = 2000, message
            = "Annotation Me must be between 20 and 2000 characters")
    private String annotation;

    private Long category;

    @Size(min = 20, max = 7000, message
            = "Description Me must be between 20 and 7000 characters")
    private String description;

    private String eventDate;

    private Location location;

    private Boolean paid;

    @PositiveOrZero
    private Integer participantLimit;

    private Boolean requestModeration;

    private State stateAction;

    @Size(min = 3, max = 120, message
            = "Title Me must be between 3 and 120 characters")
    private String title;

}
