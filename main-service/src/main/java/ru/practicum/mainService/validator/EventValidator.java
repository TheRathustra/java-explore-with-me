package ru.practicum.mainService.validator;

import org.springframework.stereotype.Component;
import ru.practicum.mainService.dto.event.NewEventDto;
import ru.practicum.mainService.dto.event.UpdateEventUserRequest;
import ru.practicum.mainService.error.exception.event.EventValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class EventValidator {

    public void validateEvent(NewEventDto eventDto) {
        if (eventDto.getEventDate().isBefore(LocalDateTime.now().plus(2, ChronoUnit.HOURS))) {
            throw new EventValidationException("Field: eventDate. Error: должно содержать дату, которая еще не наступила. Value: " + eventDto.getEventDate());
        }
    }

    public void validateEvent(UpdateEventUserRequest eventDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        LocalDateTime eventDate = LocalDateTime.parse(eventDto.getEventDate(), formatter);
        if (eventDate.isBefore(LocalDateTime.now().plus(2, ChronoUnit.HOURS))) {
            throw new EventValidationException("Field: eventDate. Error: должно содержать дату, которая еще не наступила. Value: " + eventDto.getEventDate());
        }
    }

}
