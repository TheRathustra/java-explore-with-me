package ru.practicum.mainService.service.impl.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.request.ParticipationRequestDto;
import ru.practicum.mainService.dto.request.RequestMapper;
import ru.practicum.mainService.model.Request;
import ru.practicum.mainService.repository.privates.RequestRepositoryPrivate;
import ru.practicum.mainService.service.api.privates.RequestServicePrivate;
import ru.practicum.mainService.service.api.publics.UserServicePublic;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServicePrivateImpl implements RequestServicePrivate {

    private final RequestRepositoryPrivate repository;

    private final UserServicePublic userService;

    @Autowired
    public RequestServicePrivateImpl(RequestRepositoryPrivate repository, UserServicePublic userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<ParticipationRequestDto> getUserRequests(Long userId) {

        userService.getUserById(userId);

        List<Request> requests = repository.findAllByRequesterId(userId);
        return requests.stream().map(RequestMapper::requestToParticipationRequestDto).collect(Collectors.toList());

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
