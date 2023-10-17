package gang.kaewwan.kaewwanbackend.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gang.kaewwan.kaewwanbackend.rest.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
