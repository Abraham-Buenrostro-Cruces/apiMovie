package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.infrastructure.entity.MovieEntity;

import java.util.List;

public class MovieDetailedMapper {
    public static MovieDetailedDto toDto(
            final MovieEntity movie,
            final Double averageRating,
            final List<CategoryDto> categories,
            final Integer userReview
            ) {

        return new MovieDetailedDto(
                movie.getId(),
                movie.getTitle(),
                movie.getImageUrl(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getDurationMinutes(),
                averageRating,
                userReview,
                categories
        );
    }
}
