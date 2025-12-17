package mx.badak.movies.application;


import lombok.extern.slf4j.Slf4j;
import mx.badak.movies.domain.model.MovieCreatedDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.domain.service.MovieService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

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

}
