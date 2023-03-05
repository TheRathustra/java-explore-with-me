package ru.practicum.mainService.controller.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.ParticipationRequestDto;
import ru.practicum.mainService.service.api.privates.RequestServicePrivate;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
public class RequestControllerPrivate {

    private final RequestServicePrivate requestService;

    @Autowired
    public RequestControllerPrivate(RequestServicePrivate requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public ParticipationRequestDto getUserRequests(@PathVariable(name = "userId") Long userId) {
        return requestService.getUserRequests(userId);
    }

    @PostMapping
    public ParticipationRequestDto addParticipationRequest(@PathVariable(name = "userId") Long userId,
                                                           @RequestParam(name = "eventId") Long eventId) {
        return requestService.addParticipationRequest(userId, eventId);
    }

    @PatchMapping(path = "{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable(name = "userId") Long userId,
                                                 @PathVariable(name = "requestId") Long requestId) {
        return requestService.cancelRequest(userId, requestId);
    }


}
