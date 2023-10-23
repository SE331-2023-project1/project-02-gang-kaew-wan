package gang.kaewwan.kaewwanbackend.rest.dao;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewDao {

    Page<Review> getReviews();
    Review getReviewById(Long id);
    Review addReview(Review review);

}
