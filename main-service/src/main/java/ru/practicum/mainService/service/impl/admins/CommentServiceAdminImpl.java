package ru.practicum.mainService.service.impl.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.error.exception.comment.CommentNotFoundException;
import ru.practicum.mainService.model.Comment;
import ru.practicum.mainService.repository.admins.CommentRepositoryAdmin;
import ru.practicum.mainService.service.api.admins.CommentServiceAdmin;

import java.util.Optional;

@Service
public class CommentServiceAdminImpl implements CommentServiceAdmin {

    private final CommentRepositoryAdmin repository;

    @Autowired
    public CommentServiceAdminImpl(CommentRepositoryAdmin repository) {
        this.repository = repository;
    }

    @Override
    public void deleteComment(Long commentId) {
        Optional<Comment> commentOptional = repository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new CommentNotFoundException("Comment with id=" + commentId + " was not found");
        }

        repository.delete(commentOptional.get());
    }
}
