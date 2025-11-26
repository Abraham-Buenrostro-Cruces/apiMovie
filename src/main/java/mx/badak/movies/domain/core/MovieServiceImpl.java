package mx.badak.movies.domain.core;

import mx.badak.movies.domain.mapper.MovieMapper;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.domain.port.MovieRepositoryDB;
import mx.badak.movies.domain.service.MovieService;
import mx.badak.movies.infrastructure.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepositoryDB movieRepositoryDB;

    @Override
    public List<MovieDto> getAllMovies() {
        try {
            List<MovieEntity> movies = movieRepositoryDB.findAll();
            return MovieMapper.mapPeliculas(movies);
        }catch (Exception e){
            throw new RuntimeException("Error al obtener las películas", e);
        }
    }
}
