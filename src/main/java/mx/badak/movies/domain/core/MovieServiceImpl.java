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
    private MovieDetailedMapper movieDetailedMapper;

    @Autowired
    private CategoryRepositoryDB categoryRepositoryDB;

    private MovieCreatedDto movieCreated;


    @Override
    public List<MovieDto> getAllMovies() {
        try {
            List<MovieEntity> movies = movieRepositoryDB.findAll();

            Map<Integer, List<CategoryDto>> movieCategories = movies.stream()
                    .collect(Collectors.toMap(
                            MovieEntity::getId,
                            movie -> categoryService.getCategoriesByMovieId(movie.getId())
                    ));

            return MovieMapper.mapPeliculas(movies, movieCategories);
        }catch (Exception e){
            throw new RuntimeException(Constants.ERROR_MOVIES, e);
        }
    }

    @Override
    public MovieDetailedDto getMovieById(Integer movieId) {
        try {
            Optional<MovieEntity> optionalMovie = movieRepositoryDB.findById(movieId);

            if (optionalMovie.isEmpty()) {
                throw new RuntimeException(Constants.ERROR_MOVIES_ID + movieId);
            }

            MovieEntity movie = optionalMovie.get();

            List<CategoryDto> categories = categoryService.getCategoriesByMovieId(movieId);

            return movieDetailedMapper.toDto(movie, categories);

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

}
