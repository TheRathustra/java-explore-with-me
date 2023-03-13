package ru.practicum.mainService.service.impl.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.comment.CommentDto;
import ru.practicum.mainService.dto.comment.CommentMapper;
import ru.practicum.mainService.dto.comment.NewCommentDto;
import ru.practicum.mainService.error.exception.comment.CommentNotFoundException;
import ru.practicum.mainService.error.exception.comment.NotAuthorOfCommentException;
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
    public CommentDto create(Long userId, Long eventId, NewCommentDto comment) {
        User user = userService.getUserById(userId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new EventNotFoundException("Event with id=" + eventId + " was not found");
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
    public List<CommentDto> getEventComments(Long eventId, Integer from, Integer size) {
        int page = from / size;
        Pageable pageRequest = PageRequest.of(page, size);
        List<Comment> comments = repository.findAllByEventId(eventId, pageRequest);
        return comments.stream().map(CommentMapper::entityToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long userId, Long eventId, Long commentId) {
        User user = userService.getUserById(userId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new EventNotFoundException("Event with id=" + eventId + " was not found");
        }

        Optional<Comment> commentOptional = repository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new CommentNotFoundException("Comment with id=" + commentId + " was not found");
        }

        Comment comment = commentOptional.get();
        if (comment.getAuthor() != user) {
            throw new NotAuthorOfCommentException("Comment can delete only author");
        }

        repository.delete(comment);
    }

}
