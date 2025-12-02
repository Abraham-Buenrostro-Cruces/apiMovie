package mx.badak.movies.application;

import mx.badak.movies.domain.model.ReviewDto;
import mx.badak.movies.domain.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ReviewDto createReview(@RequestBody ReviewDto dto) {
        return reviewService.createReview(dto);
    }

    // Obtener reseña por ID
    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Integer id) {
        return reviewService.getReviewById(id);
    }

    // Eliminar reseña
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }

    // Obtener reseñas por película
    @GetMapping("/movie/{movieId}")
    public List<ReviewDto> getReviewsByMovieId(@PathVariable Integer movieId) {
        return reviewService.getReviewsByMovieId(movieId);
    }

    // Obtener reseñas por usuario
    @GetMapping("/user/{userId}")
    public List<ReviewDto> getReviewsByUserId(@PathVariable Integer userId) {
        return reviewService.getReviewsByUserId(userId);
    }
}
