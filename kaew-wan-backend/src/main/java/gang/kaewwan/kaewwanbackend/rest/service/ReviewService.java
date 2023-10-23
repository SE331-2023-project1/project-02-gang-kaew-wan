package gang.kaewwan.kaewwanbackend.rest.service;

import java.util.List;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;

public interface ReviewService {
    List<Review> getReviews();

    List<Review> getReviewsByTeacherId(Long id);

    Review getReviewById(Long id);

    Review addReview(Review review);
}
