package ru.practicum.mainService.service.api.admins;

import ru.practicum.mainService.dto.comment.CommentShortDto;

import java.util.List;

public interface CommentServiceAdmin {

    List<CommentShortDto> getAllComments(Integer from, Integer size);

    void deleteComment(Long commentId);

}
