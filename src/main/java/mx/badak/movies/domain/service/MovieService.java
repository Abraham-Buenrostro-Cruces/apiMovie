package mx.badak.movies.domain.service;

import mx.badak.movies.domain.model.MovieCreatedDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    MovieDetailedDto getMovieById(Integer movieId);
    boolean deleteById(Integer movieId);
    void postMovie(MovieCreatedDto createdDto);
    void updateMovie(Integer movieId, MovieCreatedDto updatedDto);
}
