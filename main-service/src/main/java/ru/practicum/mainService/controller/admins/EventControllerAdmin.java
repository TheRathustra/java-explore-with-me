package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.EventFullDto;
import ru.practicum.mainService.dto.event.UpdateEventAdminRequest;
import ru.practicum.mainService.service.api.admins.EventServiceAdmin;

import java.util.List;

@RestController
@RequestMapping(path = "admin/events")
public class EventControllerAdmin {

    private final EventServiceAdmin eventService;

    @Autowired
    public EventControllerAdmin(EventServiceAdmin eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public EventFullDto getEvents(@RequestParam(name = "users") List<Long> users,
                                  @RequestParam(name = "states") List<String> states,
                                  @RequestParam(name = "categories") List<Long> categories,
                                  @RequestParam(name = "rangeStart") String rangeStart,
                                  @RequestParam(name = "rangeEnd") String rangeEnd,
                                  @RequestParam(name = "from", defaultValue = "0") Integer from,
                                  @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return null;

    }

    @PatchMapping(path = "/eventId")
    public EventFullDto updateEvent(@PathVariable(name = "eventId") Long eventId, @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }

}
