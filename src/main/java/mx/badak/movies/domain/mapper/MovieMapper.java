package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.model.MovieCreatedDto;
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

    public static MovieEntity toEntity(MovieCreatedDto movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movie.title());
        movieEntity.setImageUrl(movie.imageUrl());
        movieEntity.setDescription(movie.description());
        movieEntity.setReleaseYear(movie.releaseYear());
        movieEntity.setDurationMinutes(movie.durationMinutes());
        return movieEntity;
    }
}
