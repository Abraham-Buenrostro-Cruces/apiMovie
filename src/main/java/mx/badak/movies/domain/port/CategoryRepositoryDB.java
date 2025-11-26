package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CategoryRepositoryDB extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = """
            SELECT
            c.*
        FROM movie m
        JOIN movie_category mc ON mc.movie_id = m.id
        JOIN category c ON c.id = mc.category_id
        WHERE m.id = :m.id
                """, nativeQuery = true)
    List<CategoryEntity> getCategoriesByMovieId(@Param("m.id")Integer movieId);
}
