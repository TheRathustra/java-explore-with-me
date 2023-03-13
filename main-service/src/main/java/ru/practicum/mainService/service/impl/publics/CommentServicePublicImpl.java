package ru.practicum.mainService.service.impl.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.comment.CommentDto;
import ru.practicum.mainService.dto.comment.CommentMapper;
import ru.practicum.mainService.dto.comment.NewCommentDto;
import ru.practicum.mainService.error.exception.event.EventNotFoundException;
import ru.practicum.mainService.model.Comment;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.model.User;
import ru.practicum.mainService.repository.publics.CommentRepositoryPublic;
import ru.practicum.mainService.repository.publics.EventRepositoryPublic;
import ru.practicum.mainService.service.api.publics.CommentServicePublic;
import ru.practicum.mainService.service.api.publics.UserServicePublic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServicePublicImpl implements CommentServicePublic {

    private final CommentRepositoryPublic repository;

    private final UserServicePublic userService;

    private final EventRepositoryPublic eventRepository;

    @Autowired
    public CommentServicePublicImpl(CommentRepositoryPublic repository, UserServicePublic userService, EventRepositoryPublic eventRepository) {
        this.repository = repository;
        this.userService = userService;
        this.eventRepository = eventRepository;
    }

    @Override
    public CommentDto create(NewCommentDto comment) {
        User user = userService.getUserById(comment.getAuthor());
        Optional<Event> eventOptional = eventRepository.findById(comment.getEvent());
        if (eventOptional.isEmpty()) {
            throw new EventNotFoundException("Event with id=" + comment.getEvent() + " was not found");
        }

        Event event = eventOptional.get();

        Comment newComment = new Comment()
                .setAuthor(user)
                .setCreated(LocalDateTime.now())
                .setEvent(event)
                .setText(comment.getText());

        Comment savedComment = repository.save(newComment);

        return CommentMapper.entityToCommentDto(savedComment);
    }

    @Override
    public List<CommentDto> getEventComments(Long eventId) {
        List<Comment> comments = repository.findAllByEventId(eventId);
        return comments.stream().map(CommentMapper::entityToCommentDto).collect(Collectors.toList());
    }

}
