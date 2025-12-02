package mx.badak.movies.application;

import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
