package ru.practicum.mainService.service.impl.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.comment.CommentMapper;
import ru.practicum.mainService.dto.comment.CommentShortDto;
import ru.practicum.mainService.error.exception.comment.CommentNotFoundException;
import ru.practicum.mainService.model.Comment;
import ru.practicum.mainService.repository.admins.CommentRepositoryAdmin;
import ru.practicum.mainService.service.api.admins.CommentServiceAdmin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceAdminImpl implements CommentServiceAdmin {

    private final CommentRepositoryAdmin repository;

    @Autowired
    public CommentServiceAdminImpl(CommentRepositoryAdmin repository) {
        this.repository = repository;
    }

    @Override
    public List<CommentShortDto> getAllComments(Integer from, Integer size) {
        int page = from / size;
        Pageable pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).toList().stream()
                .map(CommentMapper::entityToCommentShortDto).collect(Collectors.toList());
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
