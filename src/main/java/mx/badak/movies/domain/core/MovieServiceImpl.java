package mx.badak.movies.domain.core;

import lombok.extern.slf4j.Slf4j;
import mx.badak.movies.domain.mapper.MovieDetailedMapper;
import mx.badak.movies.domain.mapper.MovieMapper;
import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.model.MovieCreatedDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.domain.port.CategoryRepositoryDB;
import mx.badak.movies.domain.port.MovieRepositoryDB;
import mx.badak.movies.domain.port.ReviewRepositoryDB;
import mx.badak.movies.domain.service.CategoryService;
import mx.badak.movies.domain.service.MovieService;
import mx.badak.movies.infrastructure.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.badak.movies.utils.Constants;

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

    @Autowired
    private CategoryRepositoryDB categoryRepositoryDB;

    private MovieCreatedDto movieCreated;


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
            throw new RuntimeException(Constants.ERROR_MOVIES, e);
        }

    }

    @Override
    public MovieDetailedDto getMovieById(final Integer movieId, final Integer userId) {
        try {
            Optional<MovieEntity> optionalMovie = movieRepositoryDB.findById(movieId);

            if (optionalMovie.isEmpty()) {
                throw new RuntimeException(Constants.ERROR_MOVIES_ID + movieId);
            }

            MovieEntity movie = optionalMovie.get();
            List<CategoryDto> categories = categoryService.getCategoriesByMovieId(movieId);
            Double averageRating = reviewRepositoryDB.averageRatingByMovieId(movieId);
            Integer userRating = reviewRepositoryDB.getRatingByMovieIdAndUserId(movieId, userId);
            return MovieDetailedMapper.toDto(movie, averageRating, categories, userRating);

        } catch (Exception e) {
            throw new RuntimeException(Constants.ERROR_GET_MOVIE + movieId, e);
        }
    }

    @Transactional
    @Override
    public void postMovie(MovieCreatedDto movieDto) {
        try {
            MovieEntity movieEntity = MovieMapper.toEntity(movieDto);
            MovieEntity newMovie= movieRepositoryDB.save(movieEntity);
            Integer movieId = newMovie.getId();

            for (Integer categoryId : movieDto.genres()) {
                categoryRepositoryDB.insertMovieCategory(movieId, categoryId);
            }
        } catch (Exception e) {
            throw new RuntimeException(Constants.ERROR_SAVE_MOVIE, e);
        }
    }

    @Transactional
    @Override
    public void updateMovie(Integer movieId, MovieCreatedDto updatedDto) {
        try {
            Optional<MovieEntity> optionalMovie = movieRepositoryDB.findById(movieId);
            if (optionalMovie.isEmpty()) {
                throw new RuntimeException(Constants.ERROR_MOVIES_ID + movieId);
            }
            MovieEntity movieEntity = optionalMovie.get();
            movieEntity.setTitle(updatedDto.title());
            movieEntity.setImageUrl(updatedDto.imageUrl());
            movieEntity.setDescription(updatedDto.description());
            movieEntity.setReleaseYear(updatedDto.releaseYear());
            movieEntity.setDurationMinutes(updatedDto.durationMinutes());
            movieRepositoryDB.save(movieEntity);

            categoryRepositoryDB.deleteMovieCategories(movieId);
            for (Integer categoryId : updatedDto.genres()) {
                categoryRepositoryDB.insertMovieCategory(movieId, categoryId);
            }
        } catch (Exception e) {
            throw new RuntimeException(Constants.ERROR_UPDATE_MOVIE + movieId, e);

        }

    }

    @Override
    public boolean deleteById( final Integer id) {
        if(movieRepositoryDB.existsById(id)) {
            movieRepositoryDB.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<MovieDto> getMoviesByCategory(Integer categoryId, int page, int size) {
        try {
            // Obtiene todas las películas
            List<MovieEntity> movies = movieRepositoryDB.findAll();

            // Filtra películas por categoría usando tu servicio existente
            List<MovieEntity> filteredMovies = movies.stream()
                    .filter(movie -> {
                        List<CategoryDto> categories = categoryService.getCategoriesByMovieId(movie.getId());
                        return categories.stream().anyMatch(cat -> cat.id().equals(categoryId));
                    })
                    .toList();

            // Armado de mapas igual que en getAllMovies
            Map<Integer, List<CategoryDto>> movieCategories = filteredMovies.stream()
                    .collect(Collectors.toMap(
                            MovieEntity::getId,
                            movie -> categoryService.getCategoriesByMovieId(movie.getId())
                    ));

            Map<Integer, Double> movieAverageRating = filteredMovies.stream()
                    .collect(Collectors.toMap(
                            MovieEntity::getId,
                            movie -> reviewRepositoryDB.averageRatingByMovieId(movie.getId())
                    ));

            Map<Integer, Integer> movieTotalReviews = filteredMovies.stream()
                    .collect(Collectors.toMap(
                            MovieEntity::getId,
                            movie -> reviewRepositoryDB.totalReviewsByMovieId(movie.getId())
                    ));

            List<MovieDto> dtos = MovieMapper.mapMovies(
                    filteredMovies,
                    movieCategories,
                    movieAverageRating,
                    movieTotalReviews
            );

            int start = Math.min(page * size, dtos.size());
            int end = Math.min(start + size, dtos.size());

            return dtos.subList(start, end);

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener películas por categoría", e);
        }
    }
}
