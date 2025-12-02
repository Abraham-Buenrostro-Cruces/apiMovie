package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.CategoryEntity;
import mx.badak.movies.infrastructure.entity.MovieCategory;
import mx.badak.movies.infrastructure.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepositoryDB extends JpaRepository<MovieEntity, Integer> {
    @Query(value = """
            SELECT
            m.id as id,
            m.title as title,
            m.description as description,
            c.id as idCategory,
            c.name_ as name
        FROM movie m
        JOIN movie_category mc
        JOIN category c 
        
                """, nativeQuery = true)
    List<MovieCategory> getCategoriesByMovieId();
}
