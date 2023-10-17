package gang.kaewwan.kaewwanbackend.rest.controller;


import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import gang.kaewwan.kaewwanbackend.rest.entity.CommentDTO;
import gang.kaewwan.kaewwanbackend.rest.service.CommentService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("comments/{id}")
    public CommentDTO getCommentById(@PathVariable("id") Long id){
        return RestMapper.INSTANCE.getCommentDto(commentService.getCommentById(id));
    }

    @GetMapping("comments/{personId}")
    public List<CommentDTO> getCommentByStudentID(@PathVariable("id") Long id){
        Page<Comment> result = commentService.getCommentByStudentId(id);
        return RestMapper.INSTANCE.getCommentDto(result.getContent());
    }

}
