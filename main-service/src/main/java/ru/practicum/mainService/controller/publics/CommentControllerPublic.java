package ru.practicum.mainService.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.comment.CommentDto;
import ru.practicum.mainService.dto.comment.NewCommentDto;
import ru.practicum.mainService.service.api.publics.CommentServicePublic;
import ru.practicum.mainService.validator.CommentValidator;

import java.util.List;

@RestController
@RequestMapping(path = "/comments")
public class CommentControllerPublic {

    private final CommentServicePublic commentService;

    private final CommentValidator validator;

    @Autowired
    public CommentControllerPublic(CommentServicePublic commentService, CommentValidator validator) {
        this.commentService = commentService;
        this.validator = validator;
    }

    @GetMapping(path = "/{eventId}")
    public List<CommentDto> getEventComments(@PathVariable(name = "eventId") Long eventId) {
        return commentService.getEventComments(eventId);
    }

    @PostMapping
    public CommentDto create(@RequestBody NewCommentDto comment) {
        validator.validateComment(comment);
        return commentService.create(comment);
    }

}
