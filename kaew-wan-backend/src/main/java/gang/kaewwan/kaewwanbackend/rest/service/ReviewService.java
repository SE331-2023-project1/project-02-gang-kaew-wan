package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Page<Review> getReviews();

    Review getReviewById(Long id);

    Review addReview(Review review);
}
