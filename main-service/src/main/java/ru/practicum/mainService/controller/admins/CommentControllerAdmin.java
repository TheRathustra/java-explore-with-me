package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.comment.CommentShortDto;
import ru.practicum.mainService.service.api.admins.CommentServiceAdmin;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CommentControllerAdmin {

    private final CommentServiceAdmin commentService;

    @Autowired
    public CommentControllerAdmin(CommentServiceAdmin commentService) {
        this.commentService = commentService;
    }

    /**
     *
     Администратор может посмотреть все комментарии
     */
    @GetMapping(path = "/comments")
    public ResponseEntity<List<CommentShortDto>> getAllComments(@RequestParam(name = "from", defaultValue = "0") Integer from,
                                                                @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<CommentShortDto> comments = commentService.getAllComments(from, size);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * @param commentId - удаляемый комментарий
     * Администратор может удалить любой комментарий
     */
    @DeleteMapping(path = "/comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
