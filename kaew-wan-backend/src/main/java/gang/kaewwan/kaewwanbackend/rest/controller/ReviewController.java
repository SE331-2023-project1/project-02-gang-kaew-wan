package gang.kaewwan.kaewwanbackend.rest.controller;

import gang.kaewwan.kaewwanbackend.rest.entity.Review;
import gang.kaewwan.kaewwanbackend.rest.entity.ReviewDTO;
import gang.kaewwan.kaewwanbackend.rest.service.ReviewService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    final ReviewService reviewService;

    @GetMapping("reviews")
    public List<ReviewDTO> getReviews() {
        Page<Review> result = reviewService.getReviews();
        return RestMapper.INSTANCE.getReviewDTO(result.getContent());
    }

    @GetMapping("reviews/{id}")
    public ReviewDTO getReviewById(@PathVariable("id") Long id ){
        Review result = reviewService.getReviewById(id);
        return RestMapper.INSTANCE.getReviewDTO(result);
    }

    @PostMapping("reviews")
    public ReviewDTO addReview(@RequestBody Review review ){
        Review result = reviewService.addReview(review);
        return RestMapper.INSTANCE.getReviewDTO(result);
    }

}
