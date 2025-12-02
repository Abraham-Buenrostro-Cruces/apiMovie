package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepositoryDB extends JpaRepository<ReviewEntity, Integer> {

    List<ReviewEntity> findByMovieId(Integer movieId);

    List<ReviewEntity> findByUserId(Integer userId);
}
