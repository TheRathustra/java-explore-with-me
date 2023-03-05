package ru.practicum.mainService.service.impl.privates;

import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.event.ParticipationRequestDto;
import ru.practicum.mainService.service.api.privates.RequestServicePrivate;

@Service
public class RequestServicePrivateImpl implements RequestServicePrivate {

    @Override
    public ParticipationRequestDto getUserRequests(Long userId) {
        return null;
    }

    @Override
    public ParticipationRequestDto addParticipationRequest(Long userId, Long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        return null;
    }
}
