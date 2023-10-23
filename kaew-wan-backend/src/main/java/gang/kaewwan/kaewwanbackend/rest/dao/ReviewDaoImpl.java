package gang.kaewwan.kaewwanbackend.rest.dao;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import gang.kaewwan.kaewwanbackend.rest.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class ReviewDaoImpl implements ReviewDao {
    final ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review addReview(Review review) {
        // check student review for 1st time
        if (reviewRepository.findByStudent_Id(review.getStudent().getId()) != null) {
            Review oldReview = reviewRepository.findByStudent_Id(review.getStudent().getId());
            review.setId(oldReview.getId());
        }
        return reviewRepository.save(review);

    }

    @Override
    public List<Review> getReviewsByTeacherId(Long id) {
        return reviewRepository.findByTeacher_Id(id);
    }

}
