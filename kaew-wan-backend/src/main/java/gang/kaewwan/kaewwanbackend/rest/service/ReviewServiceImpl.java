package gang.kaewwan.kaewwanbackend.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gang.kaewwan.kaewwanbackend.rest.dao.ReviewDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    final ReviewDao reviewDao;

    @Override
    public List<Review> getReviews() {
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

    @Override
    public List<Review> getReviewsByTeacherId(Long id) {
        return reviewDao.getReviewsByTeacherId(id);
    }
}
