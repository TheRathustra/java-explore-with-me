package ru.practicum.mainService.dto.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.user.UserShortDto;
import ru.practicum.mainService.model.Location;
import ru.practicum.mainService.model.State;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EventFullDto {

    private Long id;

    private String annotation;

    private CategoryDto category;

    private Integer confirmedRequests;

    private String createdOn;

    private String description;

    private String eventDate;

    private UserShortDto initiator;

    private Location location;

    private Boolean paid;

    private Integer participantLimit;

    private String publishedOn;

    private Boolean requestModeration;

    private State state;

    private String title;

    private Long views;

}
