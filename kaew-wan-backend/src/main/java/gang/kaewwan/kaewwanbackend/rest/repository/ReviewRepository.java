package gang.kaewwan.kaewwanbackend.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTeacher_Id(Long id);

    Review findByStudent_Id(Long id);
}
