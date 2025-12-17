package mx.badak.movies.application;

import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies(
            @RequestParam (defaultValue = "0") final int page,
            @RequestParam (defaultValue = "8") final int size,
            @RequestParam(required = false) final String name) {
        return movieService.getAllMovies(page, size, name);
    }

    @GetMapping("/details/{id}")
    public MovieDetailedDto getDetailsByMovieById(
            @PathVariable("id") final Integer movieId,
            @RequestParam final Integer userId
    ) {
        return movieService.getMovieById(movieId, userId);
    }
}
