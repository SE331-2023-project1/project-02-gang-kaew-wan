package gang.kaewwan.kaewwanbackend.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import gang.kaewwan.kaewwanbackend.rest.entity.ReviewDTO;
import gang.kaewwan.kaewwanbackend.rest.service.ReviewService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    final ReviewService reviewService;

    @GetMapping("reviews")
    public List<ReviewDTO> getReviews() {
        List<Review> result = reviewService.getReviews();
        return RestMapper.INSTANCE.getReviewDTO(result);
    }

    @GetMapping("reviews/{id}")
    public List<ReviewDTO> getReviewByTeacherId(@PathVariable("id") Long id) {
        List<Review> result = reviewService.getReviewsByTeacherId(id);
        return RestMapper.INSTANCE.getReviewDTO(result);
    }

    @GetMapping("review/{id}")
    public ReviewDTO getReviewById(@PathVariable("id") Long id) {
        Review result = reviewService.getReviewById(id);
        return RestMapper.INSTANCE.getReviewDTO(result);
    }

    @PostMapping("reviews")
    public ReviewDTO addReview(@RequestBody Review review) {
        Review result = reviewService.addReview(review);
        return RestMapper.INSTANCE.getReviewDTO(result);
    }

}
