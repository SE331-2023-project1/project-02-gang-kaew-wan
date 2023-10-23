package gang.kaewwan.kaewwanbackend.rest.repository;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByTeacher_Id(Long id, Pageable pageable);
    Review findByStudent_Id(Long id);
}
