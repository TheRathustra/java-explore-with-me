package ru.practicum.mainService.service.api.admins;

import ru.practicum.mainService.dto.event.EventFullDto;
import ru.practicum.mainService.dto.event.UpdateEventAdminRequest;

import java.util.List;

public interface EventServiceAdmin {

    List<EventFullDto> getEvents(List<Long> users,
                                 List<String> states,
                                 List<Long> categories,
                                 String rangeStart,
                                 String rangeEnd,
                                 Integer from,
                                 Integer size);

    EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);

}
