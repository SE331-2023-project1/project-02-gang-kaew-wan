package gang.kaewwan.kaewwanbackend.rest.dao;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import gang.kaewwan.kaewwanbackend.rest.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class ReviewDaoImpl implements ReviewDao {
    final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getReviews() {
        return reviewRepository.findAll(Pageable.unpaged());
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review addReview(Review review) {
        // check student review for 1sd time
        if (reviewRepository.findByStudent_Id(review.getStudent().getId()) != null) {
            Review oldReview = reviewRepository.findByStudent_Id(review.getStudent().getId());
            review.setId(oldReview.getId());
        }
        return reviewRepository.save(review);

    }


}
