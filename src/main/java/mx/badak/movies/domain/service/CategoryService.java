package mx.badak.movies.domain.service;

import mx.badak.movies.domain.model.CategoryDto;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategoriesByMovieId(Integer movieId);
    List<CategoryDto> getAllCategories();
}
