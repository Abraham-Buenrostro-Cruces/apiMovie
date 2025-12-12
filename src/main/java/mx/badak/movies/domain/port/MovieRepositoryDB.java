package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import mx.badak.movies.infrastructure.entity.RelatedMovieProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepositoryDB extends JpaRepository<MovieEntity, Integer> {
    @Query(value = "WITH original_categories AS (\n" +
            "    SELECT category_id FROM movie_category WHERE movie_id = :movieId\n" +
            ")\n" +
            "SELECT m.id, m.title, m.image_url AS imageUrl\n" +
            "FROM movie m\n" +
            "LEFT JOIN movie_category mc ON m.id = mc.movie_id\n" +
            "LEFT JOIN original_categories oc ON mc.category_id = oc.category_id\n" +
            "LEFT JOIN review r ON m.id = r.movie_id\n" +
            "WHERE m.id != :movieId\n" +
            "GROUP BY m.id, m.title, m.image_url\n" +
            "ORDER BY COUNT(DISTINCT oc.category_id) DESC, COALESCE(AVG(r.rating), 0) DESC\n" +
            "LIMIT 10", 
            nativeQuery = true)
    List<RelatedMovieProjection> findRelatedMoviesByCategoryAndRating(@Param("movieId") Integer movieId);

}
