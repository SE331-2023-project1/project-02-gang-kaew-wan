package gang.kaewwan.kaewwanbackend.rest.controller;


import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import gang.kaewwan.kaewwanbackend.rest.entity.CommentDTO;
import gang.kaewwan.kaewwanbackend.rest.service.CommentService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    final CommentService commentService;

    @GetMapping("comments")
    public List<CommentDTO> getCommentLists() {
        Page<Comment> result = commentService.getComments();
        return RestMapper.INSTANCE.getCommentDto(result.getContent());
    }

    @GetMapping("comments/{personId}")
    public List<CommentDTO> getCommentByStudentID(@PathVariable("personId") Long id) {
        Page<Comment> result = commentService.getCommentByStudentId(id);
        return RestMapper.INSTANCE.getCommentDto(result.getContent());
    }

    //Get comment by Comment Id
    @GetMapping("comment/{id}")
    public CommentDTO getCommentByCommentId(@PathVariable("id") Long id) {
        return RestMapper.INSTANCE.getCommentDto(commentService.getCommentById(id));
    }

    @PostMapping("comments")
    public CommentDTO addCommentToPerson(@RequestBody Comment comment) {
        Comment result = commentService.addComment(comment);
        return RestMapper.INSTANCE.getCommentDto(result);
    }

    @PutMapping("comment/{id}")
    public CommentDTO editComment(@RequestBody Comment comment, @PathVariable("id") Long id) {
        Comment result = commentService.updateComment(id, comment);
        return RestMapper.INSTANCE.getCommentDto(result);
    }

    @DeleteMapping("comment/{id}")
    public CommentDTO deleteComment(@PathVariable("id") Long id) {
        Comment result = commentService.deleteComment(id);
        return RestMapper.INSTANCE.getCommentDto(result);
    }

    @PostMapping("reply/{CommentParentId}")
    public CommentDTO addReplyComment(@RequestBody Comment comment, @PathVariable("CommentParentId") Long id) {
        Comment result = commentService.replyComment(id, comment);
        return RestMapper.INSTANCE.getCommentDto(result);
    }
}
