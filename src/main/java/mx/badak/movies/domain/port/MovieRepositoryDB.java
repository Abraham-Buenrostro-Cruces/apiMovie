package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepositoryDB extends JpaRepository<MovieEntity, Integer> {
}
