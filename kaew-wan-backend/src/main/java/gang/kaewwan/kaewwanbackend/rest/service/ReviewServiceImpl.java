package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.dao.ReviewDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    final ReviewDao reviewDao;

    @Override
    public Page<Review> getReviews() {
        return reviewDao.getReviews();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewDao.getReviewById(id);
    }

    @Override
    public Review addReview(Review review) {
        return reviewDao.addReview(review);
    }
}
