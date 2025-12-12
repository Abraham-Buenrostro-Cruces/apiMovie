package mx.badak.movies.application;

import mx.badak.movies.infrastructure.entity.RelatedMovieProjection;
import mx.badak.movies.domain.port.MovieRepositoryDB;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.domain.service.MovieService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieRepositoryDB movieRepositoryDB;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieDetailedDto getMovieById(@PathVariable("id") final Integer movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping("/{id_pelicula}/related")
    public ResponseEntity<List<RelatedMovieProjection>> getRelatedMovies(@PathVariable("id_pelicula") Integer idPelicula) {
        if (!movieRepositoryDB.existsById(idPelicula)) {
            return ResponseEntity.notFound().build();
        }
        List<RelatedMovieProjection> related = movieRepositoryDB.findRelatedMoviesByCategoryAndRating(idPelicula);
        return ResponseEntity.ok(related);
    }
}
