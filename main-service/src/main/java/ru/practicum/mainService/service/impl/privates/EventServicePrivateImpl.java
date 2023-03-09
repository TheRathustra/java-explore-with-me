package ru.practicum.mainService.service.impl.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.event.*;
import ru.practicum.mainService.dto.request.ParticipationRequestDto;
import ru.practicum.mainService.dto.request.RequestMapper;
import ru.practicum.mainService.error.exception.category.CategoryNotFoundException;
import ru.practicum.mainService.error.exception.event.EventIncorrectState;
import ru.practicum.mainService.error.exception.event.EventNotFoundException;
import ru.practicum.mainService.error.exception.request.IncorrectRequestStatusException;
import ru.practicum.mainService.error.exception.request.RequestParticipantLimitException;
import ru.practicum.mainService.model.*;
import ru.practicum.mainService.repository.privates.EventRepositoryPrivate;
import ru.practicum.mainService.repository.publics.CategoryRepositoryPublic;
import ru.practicum.mainService.repository.publics.RequestRepositoryPublic;
import ru.practicum.mainService.service.api.privates.EventServicePrivate;
import ru.practicum.mainService.service.api.publics.UserServicePublic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServicePrivateImpl implements EventServicePrivate {

    private final EventRepositoryPrivate repository;

    private final CategoryRepositoryPublic categoryRepository;

    private final RequestRepositoryPublic requestRepository;

    private final UserServicePublic userService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Autowired
    public EventServicePrivateImpl(EventRepositoryPrivate repository, CategoryRepositoryPublic categoryRepository, RequestRepositoryPublic requestRepository, UserServicePublic userService) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.requestRepository = requestRepository;
        this.userService = userService;
    }

    @Override
    public List<EventShortDto> getEvents(Long userId, Integer from, Integer size) {
        int page = from / size;
        Pageable pageRequest = PageRequest.of(page, size);
        List<Event> events = repository.findAllByInitiatorId(userId, pageRequest);
        return events.stream().map(EventMapper::entityToEventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto addEvent(Long userId, NewEventDto eventDto) {
        Optional<Category> category = categoryRepository.findById(eventDto.getCategory());
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category with id=" + eventDto.getCategory() + "was not found");
        }

        User user = userService.getUserById(userId);

        Event event = EventMapper.newEventDtoToEntity(eventDto);
        event.setState(State.PENDING);
        event.setCategory(category.get());
        event.setInitiator(user);
        event.setCreatedOn(LocalDateTime.now());
        event.setConfirmedRequests(0);
        Event newEvent = repository.save(event);

        return EventMapper.entityToEventFullDto(newEvent);
    }

    @Override
    public EventFullDto getEventByEventId(Long userId, Long eventId) {
        Event event = repository.findByIdAndInitiatorId(eventId, userId);
        if (event == null) {
            throw new EventNotFoundException("Event with id=" + eventId + " was not found");
        }
        return EventMapper.entityToEventFullDto(event);
    }

    @Override
    public EventFullDto patchUserEventById(Long userId, Long eventId, UpdateEventUserRequest eventDto) {

        Optional<Event> eventOptional = repository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new EventNotFoundException("Event with id=" + eventId + " was not found");
        }

        Event event = eventOptional.get();

        if (event.getState() != State.CANCELED && event.getState() != State.PENDING) {
            throw new EventIncorrectState("Event must not be published");
        }

        if (eventDto.getCategory() != null) {
            Optional<Category> category = categoryRepository.findById(eventDto.getCategory());
            if (category.isEmpty()) {
                throw new CategoryNotFoundException("Category with id=" + eventDto.getCategory() + "was not found");
            }
            event.setCategory(category.get());
        }

        if (eventDto.getAnnotation() != null)
            event.setAnnotation(eventDto.getAnnotation());
        if (eventDto.getDescription() != null)
            event.setDescription(eventDto.getDescription());
        if (eventDto.getEventDate() != null)
            event.setEventDate(LocalDateTime.parse(eventDto.getEventDate(), formatter));
        if (eventDto.getLocation() != null) {
            event.setLat(eventDto.getLocation().getLat());
            event.setLon(eventDto.getLocation().getLon());
        }
        if (eventDto.getPaid() != null)
            event.setPaid(eventDto.getPaid());
        if (eventDto.getParticipantLimit() != null)
            event.setParticipantLimit(eventDto.getParticipantLimit());
        if (eventDto.getRequestModeration() != null)
            event.setRequestModeration(eventDto.getRequestModeration());
        if (eventDto.getStateAction() != null)
            if (eventDto.getStateAction() == StateAction.SEND_TO_REVIEW) {
                event.setState(State.PUBLISHED);
            } else {
                event.setState(State.CANCELED);
            }
        if (eventDto.getTitle() != null)
            event.setTitle(eventDto.getTitle());

        Event updatedEvent = repository.save(event);

        return EventMapper.entityToEventFullDto(updatedEvent);
    }

    @Override
    public List<ParticipationRequestDto> getEventParticipants(Long userId, Long eventId) {
        List<Request> requests = requestRepository.findAllByEventIdAndRequesterId(eventId, userId);
        return requests.stream().map(RequestMapper::requestToParticipationRequestDto).collect(Collectors.toList());
    }

    @Override
    public EventRequestStatusUpdateResult changeRequestStatus(Long userId,
                                                              Long eventId,
                                                              EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {

        EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult();
        List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
        List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();

        Status newStatus = eventRequestStatusUpdateRequest.getStatus();

        userService.getUserById(userId);
        Event event = repository.findByIdAndInitiatorId(eventId, userId);
        if (event == null) {
            throw new EventNotFoundException("Event with id=" + eventId + " was not found");
        }

        Integer limit = event.getParticipantLimit();

        if ((event.getParticipantLimit() > 0 && event.getRequestModeration())
                && limit <= event.getConfirmedRequests()) {
            throw new RequestParticipantLimitException("The participant limit has been reached");
        }

        List<Request> requests = requestRepository
                .findAllByIdInAndRequesterIdAndEventId(eventRequestStatusUpdateRequest.getRequestIds(), userId, eventId);
        for (Request request : requests) {
            if (request.getStatus() != Status.PENDING) {
                throw new IncorrectRequestStatusException("Request must have status PENDING");
            }
            if (newStatus == Status.CONFIRMED) {
                confirmedRequests.add(RequestMapper.requestToParticipationRequestDto(request));
                limit++;
            }
            if (newStatus == Status.REJECTED)
                rejectedRequests.add(RequestMapper.requestToParticipationRequestDto(request));

            request.setStatus(newStatus);

            if ((event.getParticipantLimit() > 0 && event.getRequestModeration())
                    && limit <= event.getConfirmedRequests()) {
                newStatus = Status.REJECTED;
            }
        }

        requestRepository.saveAll(requests);

        result.setConfirmedRequests(confirmedRequests);
        result.setRejectedRequests(rejectedRequests);

        return result;

    }

}
