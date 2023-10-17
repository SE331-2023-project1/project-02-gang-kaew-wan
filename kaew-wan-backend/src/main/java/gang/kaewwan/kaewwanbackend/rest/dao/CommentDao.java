package gang.kaewwan.kaewwanbackend.rest.dao;

import org.springframework.data.domain.Page;

import gang.kaewwan.kaewwanbackend.rest.entity.Comment;

public interface CommentDao {
    Integer getCommentsCount();

    Page<Comment> getComments();

    Comment getCommentById(Long id);

    Page<Comment> getCommentByStudentId(Long id);

    Page<Comment> getCommentByTeacherId(Long id);

    Comment addComment(Comment comment);
    Comment updateComment(Long id, Comment comment);
    Comment replyComment(Long id, Comment comment);

}
