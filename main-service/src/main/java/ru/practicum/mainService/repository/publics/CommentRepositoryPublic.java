package ru.practicum.mainService.repository.publics;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainService.model.Comment;

import java.util.List;

public interface CommentRepositoryPublic extends JpaRepository<Comment, Long> {

    List<Comment> findAllByEventId(Long eventId);

}
