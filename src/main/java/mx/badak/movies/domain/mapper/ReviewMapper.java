package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.ReviewDto;
import mx.badak.movies.infrastructure.entity.ReviewEntity;

public class ReviewMapper {

    public static ReviewDto toDto(final ReviewEntity entity) {
        return new ReviewDto(
                entity.getId(),
                entity.getRating(),
                entity.getComment(),
                entity.getCreatedAt(),
                entity.getUserId(),
                entity.getMovieId()
        );
    }

    public static ReviewEntity toEntity(final ReviewDto dto) {
        ReviewEntity entity = new ReviewEntity();
        entity.setId(dto.id());
        entity.setRating(dto.rating());
        entity.setComment(dto.comment());
        entity.setCreatedAt(dto.createdAt());
        entity.setUserId(dto.userId());
        entity.setMovieId(dto.movieId());
        return entity;
    }
}
