package ru.practicum.mainService.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.mainService.model.Location;
import ru.practicum.mainService.model.StateAction;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventUserRequest {

    private Long id;

    @Size(min = 20, max = 2000, message
            = "Annotation must be between 20 and 2000 characters")
    private String annotation;

    private Long category;

    @Size(min = 20, max = 7000, message
            = "Description must be between 20 and 7000 characters")
    private String description;

    private String eventDate;

    private Location location;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;

    private StateAction stateAction;

    @Size(min = 3, max = 120, message
            = "Title must be between 3 and 120 characters")
    private String title;

}
