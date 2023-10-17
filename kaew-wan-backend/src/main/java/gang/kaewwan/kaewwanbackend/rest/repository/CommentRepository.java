package gang.kaewwan.kaewwanbackend.rest.repository;

import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByStudent_Id(Long id, Pageable pageable);
    Page<Comment> findByTeacher_Id(Long id, Pageable pageable);
}
