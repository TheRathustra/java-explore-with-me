package ru.practicum.mainService.service.api.privates;

import ru.practicum.mainService.dto.request.ParticipationRequestDto;

import java.util.List;

public interface RequestServicePrivate {

    List<ParticipationRequestDto> getUserRequests(Long userId);

    ParticipationRequestDto addParticipationRequest(Long userId, Long eventId);

    ParticipationRequestDto cancelRequest(Long userId, Long requestId);

}
