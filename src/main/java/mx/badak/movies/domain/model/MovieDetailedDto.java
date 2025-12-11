package mx.badak.movies.domain.model;

import java.util.List;

public record MovieDetailedDto(
    Integer id,
    String title,
    String imageUrl,
    String description,
    Integer releaseYear,
    Integer durationMinutes,
    Double averageRating,
    Integer userRating,
    List<CategoryDto> genres
) {

}
