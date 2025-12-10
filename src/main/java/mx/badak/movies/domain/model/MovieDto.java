package mx.badak.movies.domain.model;

import java.util.List;

public record MovieDto(
    Integer id,
    String title,
    String imageUrl,
    List<CategoryDto> genres,
    Integer totalReviews,
    Double averageRating
) {

}
