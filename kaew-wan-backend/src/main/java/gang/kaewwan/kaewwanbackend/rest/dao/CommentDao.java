package gang.kaewwan.kaewwanbackend.rest.dao;

import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentDao {
    Integer getCommentsCount();

    Page<Comment> getComments();

    Comment getCommentById(Long id);

    Page<Comment> getCommentByStudentId(Long id);

    Page<Comment> getCommentByTeacherId(Long id);

}
