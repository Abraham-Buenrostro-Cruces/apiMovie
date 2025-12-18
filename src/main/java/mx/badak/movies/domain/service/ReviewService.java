package mx.badak.movies.domain.service;

import mx.badak.movies.domain.model.ReviewDto;
import java.util.List;

public interface ReviewService {

    List<ReviewDto> getAllReviews();

    ReviewDto createReview(ReviewDto dto);

    ReviewDto getReviewById(Integer id);

    void deleteReview(Integer id);

    List<ReviewDto> getReviewsByMovieId(Integer movieId);

    List<ReviewDto> getReviewsByUserId(Integer userId);

    ReviewDto updateReview(Integer movieId, Integer userId, ReviewDto dto);

}
