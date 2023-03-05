package ru.practicum.mainService.service.api.privates;

import ru.practicum.mainService.dto.event.ParticipationRequestDto;

public interface RequestServicePrivate {

    ParticipationRequestDto getUserRequests(Long userId);

    ParticipationRequestDto addParticipationRequest(Long userId, Long eventId);

    ParticipationRequestDto cancelRequest(Long userId, Long requestId);

}
