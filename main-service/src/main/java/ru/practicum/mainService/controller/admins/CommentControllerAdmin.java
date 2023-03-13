package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.mainService.service.api.admins.CommentServiceAdmin;

@RestController
public class CommentControllerAdmin {

    private final CommentServiceAdmin commentService;

    @Autowired
    public CommentControllerAdmin(CommentServiceAdmin commentService) {
        this.commentService = commentService;
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
