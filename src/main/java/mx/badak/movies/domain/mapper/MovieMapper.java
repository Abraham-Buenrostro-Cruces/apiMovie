package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.infrastructure.entity.MovieEntity;

import java.util.List;

public class MovieMapper {
    public static List<MovieDto> mapPeliculas(List<MovieEntity> peliculas) {
        return peliculas.stream().map(
                p -> new MovieDto(
                        p.getId(),
                        p.getTitle(),
                        p.getImageUrl()
                )
        ).toList();
    }
}
