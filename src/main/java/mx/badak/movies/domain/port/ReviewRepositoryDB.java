package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryDB extends JpaRepository<ReviewEntity, Integer> {

    List<ReviewEntity> findByMovieId(Integer movieId);

    List<ReviewEntity> findByUserId(Integer userId);

    @Query("SELECT COUNT(r) FROM ReviewEntity r WHERE r.movieId = :movieId")
    int totalReviewsByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM ReviewEntity r WHERE r.movieId = :movieId")
    double averageRatingByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT r.rating FROM ReviewEntity r WHERE r.movieId = :movieId AND r.userId = :userId")
    Integer getRatingByMovieIdAndUserId(@Param("movieId") Integer movieId, @Param("userId") Integer userId);

    Optional<ReviewEntity> findByUserIdAndMovieId(Integer userId, Integer movieId);

}
