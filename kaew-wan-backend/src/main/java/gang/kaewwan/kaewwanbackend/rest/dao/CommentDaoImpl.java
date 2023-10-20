package gang.kaewwan.kaewwanbackend.rest.dao;

import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import gang.kaewwan.kaewwanbackend.rest.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
@Profile("db")
public class CommentDaoImpl implements CommentDao{
    final CommentRepository commentRepository;

    @Override
    public Integer getCommentsCount() {
        return Math.toIntExact(commentRepository.count());
    }

    @Override
    public Page<Comment> getComments() {
        return commentRepository.findAll(Pageable.unpaged());
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Comment> getCommentByStudentId(Long id) {
        return commentRepository.findByStudent_Id(id, Pageable.unpaged());
    }

    @Override
    public Page<Comment> getCommentByTeacherId(Long id) {
        return commentRepository.findByTeacher_Id(id, Pageable.unpaged());
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        if(commentRepository.existsById(id)){
            Comment commentData = this.getCommentById(id);
            commentData.setId(id);
            commentData.setEdited(true);
            commentData.setMessage(comment.getMessage());
            return commentRepository.save(commentData);
        }
        return null;
    }

    @Override
    public Comment replyComment(Long id, Comment comment){
        //Check Parent Exist
        if(commentRepository.existsById(id)){
            comment.setParent(this.getCommentById(id));
            return commentRepository.save(comment);
        }
        return null;
    }
}
