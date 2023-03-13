package ru.practicum.mainService.service.impl.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.request.ParticipationRequestDto;
import ru.practicum.mainService.dto.request.RequestMapper;
import ru.practicum.mainService.error.exception.event.EventNotFoundException;
import ru.practicum.mainService.error.exception.request.InvalidRequestException;
import ru.practicum.mainService.error.exception.request.RequestAlreadyExistException;
import ru.practicum.mainService.error.exception.request.RequestNotFoundException;
import ru.practicum.mainService.model.*;
import ru.practicum.mainService.repository.privates.EventRepositoryPrivate;
import ru.practicum.mainService.repository.privates.RequestRepositoryPrivate;
import ru.practicum.mainService.service.api.privates.RequestServicePrivate;
import ru.practicum.mainService.service.api.publics.UserServicePublic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServicePrivateImpl implements RequestServicePrivate {

    private final RequestRepositoryPrivate repository;

    private final UserServicePublic userService;

    private final EventRepositoryPrivate eventRepository;

    @Autowired
    public RequestServicePrivateImpl(RequestRepositoryPrivate repository, UserServicePublic userService, EventRepositoryPrivate eventRepository) {
        this.repository = repository;
        this.userService = userService;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<ParticipationRequestDto> getUserRequests(Long userId) {

        userService.getUserById(userId);

        List<Request> requests = repository.findAllByRequesterId(userId);
        return requests.stream().map(RequestMapper::requestToParticipationRequestDto).collect(Collectors.toList());

    }

    @Override
    public ParticipationRequestDto addParticipationRequest(Long userId, Long eventId) {

        User user = userService.getUserById(userId);

        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new EventNotFoundException("Event with id=" + eventId + " was not found");
        }

        Event event = eventOptional.get();
        if (event.getInitiator().getId().equals(userId)) {
            throw new InvalidRequestException("User cant request to his own event");
        }

        if (event.getState() != State.PUBLISHED) {
            throw new InvalidRequestException("Event not published");
        }

        if ((event.getParticipantLimit() <= event.getConfirmedRequests()) &&
                    event.getParticipantLimit() > 0) {
            throw new InvalidRequestException("Event reached limit");
        }

        List<Request> requests = repository.findAllByRequesterIdAndEventId(userId, eventId);
        if (!requests.isEmpty()) {
            throw new RequestAlreadyExistException("Request already exist");
        }

        Request request = new Request();
        request.setRequester(user);
        request.setEvent(event);
        request.setCreated(LocalDateTime.now());
        request.setStatus(Status.PENDING);
        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            request.setStatus(Status.CONFIRMED);
            Integer confirmedRequests = event.getConfirmedRequests();
            if (confirmedRequests == null)
                confirmedRequests = 0;
            confirmedRequests++;
            event.setConfirmedRequests(confirmedRequests);
            eventRepository.save(event);
        }

        Request newRequest = repository.save(request);

        return RequestMapper.requestToParticipationRequestDto(newRequest);
    }

    @Override
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        userService.getUserById(userId);

        Optional<Request> requestOptional = repository.findById(requestId);
        if (requestOptional.isEmpty()) {
            throw new RequestNotFoundException("Request with id=" + requestId + " was not found");
        }

        Request request = requestOptional.get();
        request.setStatus(Status.CANCELED);
        Request updatedRequest = repository.save(request);

        return RequestMapper.requestToParticipationRequestDto(updatedRequest);
    }
}
