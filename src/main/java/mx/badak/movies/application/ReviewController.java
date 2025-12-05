package mx.badak.movies.application;

import mx.badak.movies.domain.model.ReviewDto;
import mx.badak.movies.domain.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Obtener todas las reseñas
    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Crear una nueva reseña
    @PostMapping
    public ReviewDto createReview(@RequestBody final ReviewDto dto) {
        return reviewService.createReview(dto);
    }

    // Obtener reseña por ID
    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable final Integer id) {
        return reviewService.getReviewById(id);
    }

    // Eliminar reseña
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable final Integer id) {
        reviewService.deleteReview(id);
    }

    // Obtener reseñas por película
    @GetMapping("/movie/{movieId}")
    public List<ReviewDto> getReviewsByMovieId(@PathVariable final Integer movieId) {
        return reviewService.getReviewsByMovieId(movieId);
    }

    // Obtener reseñas por usuario
    @GetMapping("/user/{userId}")
    public List<ReviewDto> getReviewsByUserId(@PathVariable final Integer userId) {
        return reviewService.getReviewsByUserId(userId);
    }
}
