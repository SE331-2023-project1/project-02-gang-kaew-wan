package gang.kaewwan.kaewwanbackend.rest.dao;

import java.util.List;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;

public interface ReviewDao {

    List<Review> getReviews();

    List<Review> getReviewsByTeacherId(Long id);

    Review getReviewById(Long id);

    Review addReview(Review review);

}
