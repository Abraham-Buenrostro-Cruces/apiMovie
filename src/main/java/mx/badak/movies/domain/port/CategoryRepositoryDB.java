package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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


    @Modifying
    @Query(value = """
            INSERT INTO movie_category(movie_id, category_id) VALUES (:movieId, :categoryId)
                    """, nativeQuery = true)
    void insertMovieCategory(@Param("movieId") Integer movieId, @Param("categoryId") Integer categoryId);

    @Modifying
    @Query(value = """
            DELETE FROM movie_category WHERE movie_id = :movieId
                    """, nativeQuery = true)
    void deleteMovieCategories(@Param("movieId") Integer movieId);


}
