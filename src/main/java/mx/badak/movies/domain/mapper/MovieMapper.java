package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.infrastructure.entity.MovieEntity;

import java.util.List;
import java.util.Map;

public class MovieMapper {
    public static List<MovieDto> mapMovies(final List<MovieEntity> movies, final Map<Integer, List<CategoryDto>> movieCategories) {
        return movies.stream().map(
                p -> new MovieDto(
                        p.getId(),
                        p.getTitle(),
                        p.getImageUrl(),
                        movieCategories.get(p.getId())
                )
        ).toList();
    }
}
