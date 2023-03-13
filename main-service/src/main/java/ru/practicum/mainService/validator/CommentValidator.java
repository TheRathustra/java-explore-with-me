package ru.practicum.mainService.validator;

import org.springframework.stereotype.Component;
import ru.practicum.mainService.dto.comment.NewCommentDto;
import ru.practicum.mainService.error.exception.comment.CommentValidationException;

@Component
public class CommentValidator {

    public void validateComment(NewCommentDto comment) {
        if (comment.getText() == null || comment.getText().isEmpty() || comment.getText().isBlank()) {
            throw new CommentValidationException(
                    "Field: text. Error: Поле должно быть заполнено. Value: " + comment.getText());
        }
    }

}
