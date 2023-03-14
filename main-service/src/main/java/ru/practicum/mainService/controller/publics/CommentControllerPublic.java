package ru.practicum.mainService.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.comment.CommentDto;
import ru.practicum.mainService.dto.comment.NewCommentDto;
import ru.practicum.mainService.service.api.publics.CommentServicePublic;
import ru.practicum.mainService.validator.CommentValidator;

import java.util.List;

@RestController
public class CommentControllerPublic {

    private final CommentServicePublic commentService;

    private final CommentValidator validator;

    @Autowired
    public CommentControllerPublic(CommentServicePublic commentService, CommentValidator validator) {
        this.commentService = commentService;
        this.validator = validator;
    }

    /**
     *
     * @param eventId - событие, комментарии к которому запрашиваются
     * @return список комментариев к событию
     */
    @GetMapping(path = "/events/{eventId}/comments")
    public List<CommentDto> getEventComments(@PathVariable(name = "eventId") Long eventId,
                                             @RequestParam(name = "from", defaultValue = "0") Integer from,
                                             @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return commentService.getEventComments(eventId, from, size);
    }

    /**
     *
     * @param userId - пользователь, добавляющий комментарий
     * @param eventId - событие, к которому добавляют комментарий
     * @param comment - тело комментария
     * @return созданный комментарий
     */
    @PostMapping(path = "/users/{userId}/events/{eventId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable(name = "userId") Long userId,
                                             @PathVariable(name = "eventId") Long eventId,
                                             @RequestBody NewCommentDto comment) {
        validator.validateComment(comment);
        CommentDto savedComment = commentService.create(userId, eventId, comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    /**
     * @param userId - пользователь, добавивший комментарий
     * @param eventId - событие, к которому написан комментарий
     * @param commentId - удаляемый комментарий
     * @return NO_CONTENT если комментарий успешно удален. BAD_REQUEST в случае ошибки
     * Комментарий может удалить только его автор
     */
    @DeleteMapping(path = "/users/{userId}/events/{eventId}/comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable(name = "userId") Long userId,
                                                    @PathVariable(name = "eventId") Long eventId,
                                                    @PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(userId, eventId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
