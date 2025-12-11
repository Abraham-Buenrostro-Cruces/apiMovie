package mx.badak.movies.domain.core;

import lombok.extern.slf4j.Slf4j;
import mx.badak.movies.domain.mapper.MovieDetailedMapper;
import mx.badak.movies.domain.mapper.MovieMapper;
import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.domain.port.MovieRepositoryDB;
import mx.badak.movies.domain.port.ReviewRepositoryDB;
import mx.badak.movies.domain.service.CategoryService;
import mx.badak.movies.domain.service.MovieService;
import mx.badak.movies.infrastructure.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepositoryDB movieRepositoryDB;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ReviewRepositoryDB reviewRepositoryDB;

    @Override
    public List<MovieDto> getAllMovies(final int page, final int size) {
        try {

            List<MovieEntity> movies = movieRepositoryDB.findAll();

            Map<Integer, List<CategoryDto>> movieCategories = movies.stream()
                    .collect(Collectors.toMap(
                            MovieEntity::getId,
                            movie -> categoryService.getCategoriesByMovieId(movie.getId())
                    ));

            Map<Integer, Double> movieAverageRating = movies.stream()
                    .collect(Collectors.toMap(
                            MovieEntity::getId,
                            movie -> reviewRepositoryDB.averageRatingByMovieId(movie.getId())
                    ));

            Map<Integer, Integer> movieTotalReviews = movies.stream()
                    .collect(Collectors.toMap(
                            MovieEntity::getId,
                            movie -> reviewRepositoryDB.totalReviewsByMovieId(movie.getId())
                    ));

            List<MovieDto> dtos = MovieMapper.mapMovies(
                    movies,
                    movieCategories,
                    movieAverageRating,
                    movieTotalReviews
            );

            int start = Math.min(page * size, dtos.size());
            int end = Math.min(start + size, dtos.size());

            return dtos.subList(start, end);

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las películas", e);
        }
    }

    @Override
    public MovieDetailedDto getMovieById(final Integer movieId, final Integer userId) {
        try {
            Optional<MovieEntity> optionalMovie = movieRepositoryDB.findById(movieId);

            if (optionalMovie.isEmpty()) {
                throw new RuntimeException("Película no encontrada con id: " + movieId);
            }

            MovieEntity movie = optionalMovie.get();
            List<CategoryDto> categories = categoryService.getCategoriesByMovieId(movieId);
            Double averageRating = reviewRepositoryDB.averageRatingByMovieId(movieId);
            Integer userRating = reviewRepositoryDB.getRatingByMovieIdAndUserId(movieId, userId);
            return MovieDetailedMapper.toDto(movie, averageRating, categories, userRating);

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la película con id: " + movieId, e);
        }
    }
}
