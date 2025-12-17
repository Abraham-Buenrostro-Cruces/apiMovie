package mx.badak.movies.domain.model;

import mx.badak.movies.infrastructure.entity.MovieEntity;

import java.util.List;

public record MovieCreatedDto (
    String title,
    String imageUrl,
    String description,
    Integer releaseYear,
    Integer durationMinutes,
    List<Integer> genres
){

}
