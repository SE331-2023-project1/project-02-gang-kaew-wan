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
    public Page<Comment> getCommentByPersonId(Long id) {
        return commentDao.getCommentByPersonId(id);
    }

    @Override
    public Comment addComment(Comment id){
        return commentDao.addComment(id);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        return commentDao.updateComment(id, comment);
    }

    @Override
    public Comment deleteComment(Long id) {
        Comment comment = this.getCommentById(id);
        comment.setMessage("This comment has been deleted");
        return commentDao.updateComment(id, comment);
    }

    @Override
    public Comment replyComment(Long id, Comment comment) {
        return commentDao.replyComment(id, comment);
    }
}
