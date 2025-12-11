package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.infrastructure.entity.MovieEntity;

import java.util.List;

public class MovieDetailedMapper {
    public static MovieDetailedDto toDto(
            MovieEntity movie,
            Double averageRating,
            List<CategoryDto> categories,
            Integer userReview
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
