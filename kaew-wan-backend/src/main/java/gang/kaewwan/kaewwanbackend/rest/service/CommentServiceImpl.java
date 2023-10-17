package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.dao.CommentDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    final CommentDao commentDao;
    @Override
    public Page<Comment> getComments() {
        return commentDao.getComments();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public Page<Comment> getCommentByStudentId(Long id) {
        return commentDao.getCommentByStudentId(id);
    }

    @Override
    public Page<Comment> getCommentByTeacherId(Long id) {
        return commentDao.getCommentByTeacherId(id);
    }

    @Override
    public Comment addComment(Comment id){
        return commentDao.addComment(id);
    }
}
