package ru.practicum.mainService.service.api.publics;

import ru.practicum.mainService.dto.comment.CommentDto;
import ru.practicum.mainService.dto.comment.NewCommentDto;

import java.util.List;

public interface CommentServicePublic {

    CommentDto create(NewCommentDto comment);

    List<CommentDto> getEventComments(Long eventId);

}
