package mx.badak.movies.application;

import lombok.extern.slf4j.Slf4j;
import mx.badak.movies.domain.model.MovieCreatedDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.domain.service.MovieService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Slf4j
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

    @DeleteMapping("/{id}")
    public String deleteMovieById(@PathVariable("id") final Integer movieId) {
        boolean deleted = movieService.deleteById(movieId);
        if (deleted) {
            return "Pelicula eliminada correctamente.";
        } else {
            return "No se pudo eliminar la pelicula.";
        }
    }


    @PostMapping
    public void postMovie(@RequestBody MovieCreatedDto createdDto){
        movieService.postMovie(createdDto);

    }

    @PutMapping("/{id}" )
    public void updateMovie(@PathVariable("id") Integer movieId, @RequestBody MovieCreatedDto updatedDto) {
        movieService.updateMovie(movieId, updatedDto);
    }


    @GetMapping("/category/{categoryId}")
    public List<MovieDto> getMoviesByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        return movieService.getMoviesByCategory(categoryId, page, size);
    }
}
