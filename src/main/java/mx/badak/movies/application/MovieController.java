package mx.badak.movies.application;


import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.domain.model.MovieDto;
import mx.badak.movies.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "8") int size){
        return movieService.getAllMovies(page, size);
    }

    @GetMapping("/{id}")
    public MovieDetailedDto getMovieById(@PathVariable("id") final Integer movieId) {
        return movieService.getMovieById(movieId);
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
