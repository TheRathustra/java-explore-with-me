package ru.practicum.mainService.service.api.publics;

import ru.practicum.mainService.dto.comment.CommentDto;
import ru.practicum.mainService.dto.comment.NewCommentDto;

import java.util.List;

public interface CommentServicePublic {

    CommentDto create(Long userId, Long eventId, NewCommentDto comment);

    List<CommentDto> getEventComments(Long eventId, Integer from, Integer size);

    void deleteComment(Long userId, Long eventId, Long commentId);

}
