package ru.practicum.mainService.dto.comment;

import ru.practicum.mainService.model.Comment;

public class CommentMapper {

    public static CommentDto entityToCommentDto(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setCreated(entity.getCreated());
        dto.setAuthor(CommentDto.UserShortDto.entityToUserShortDto(entity.getAuthor()));
        dto.setEvent(CommentDto.EventShortDto.entityToEventShortDto(entity.getEvent()));
        return dto;
    }

}
