package mx.badak.movies.domain.model;

import mx.badak.movies.infrastructure.entity.CategoryEntity;

import java.util.List;

public record MovieDetailedDto(
    Integer id,
    String title,
    String imageUrl,
    String description,
    Integer releaseYear,
    Integer durationMinutes,
    List<CategoryDto> genres
) {

}
