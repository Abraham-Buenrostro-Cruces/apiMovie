package mx.badak.movies.domain.model;

import java.time.LocalDateTime;

public record ReviewDto(
        Integer id,
        Integer rating,
        String comment,
        LocalDateTime createdAt,
        Integer userId,
        Integer movieId
) { }
