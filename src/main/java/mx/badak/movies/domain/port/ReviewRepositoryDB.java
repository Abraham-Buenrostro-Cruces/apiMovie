package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepositoryDB extends JpaRepository<ReviewEntity, Integer> {

    List<ReviewEntity> findByMovieId(Integer movieId);

    List<ReviewEntity> findByUserId(Integer userId);

    @Query("SELECT COUNT(r) FROM ReviewEntity r WHERE r.movieId = :movieId")
    int totalReviewsByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM ReviewEntity r WHERE r.movieId = :movieId")
    double averageRatingByMovieId(@Param("movieId") Integer movieId);
}
