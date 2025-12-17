package mx.badak.movies.domain.service;

import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    MovieDetailedDto getMovieById(Integer movieId);
    boolean deleteById(Integer movieId);
}
