package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryDB extends JpaRepository<ReviewEntity, Integer> {

    List<ReviewEntity> findByMovieId(Integer movieId);

    List<ReviewEntity> findByUserId(Integer userId);

    Optional<ReviewEntity> findByUserIdAndMovieId(Integer userId, Integer movieId);

}
