package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {
    Page<Comment> getComments();
    Comment getCommentById(Long id);
    Page<Comment> getCommentByStudentId(Long id);
    Page<Comment> getCommentByTeacherId(Long id);

    Comment addComment(Comment id);
    Comment updateComment(Long id, Comment comment);
    Comment deleteComment(Long id);
}
