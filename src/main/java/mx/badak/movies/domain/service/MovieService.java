package mx.badak.movies.domain.service;

import mx.badak.movies.domain.model.MovieCreatedDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies(int page, int size, String name);
    MovieDetailedDto getMovieById(Integer movieId, Integer userId);
    boolean deleteById(Integer movieId);
    void postMovie(MovieCreatedDto createdDto);
    void updateMovie(Integer movieId, MovieCreatedDto updatedDto);
    List<MovieDto> getMoviesByCategory(Integer categoryId, int page, int size);
}
