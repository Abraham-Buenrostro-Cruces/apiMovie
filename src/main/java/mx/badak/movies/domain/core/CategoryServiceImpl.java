package mx.badak.movies.domain.core;

import mx.badak.movies.domain.mapper.CategoryMapper;
import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.port.CategoryRepositoryDB;
import mx.badak.movies.domain.service.CategoryService;
import mx.badak.movies.infrastructure.entity.CategoryEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepositoryDB categoryRepositoryDB;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(final CategoryRepositoryDB repositoryDB, final CategoryMapper mapper) {
        this.categoryRepositoryDB = repositoryDB;
        this.categoryMapper = mapper;
    }

    @Override
    public List<CategoryDto> getCategoriesByMovieId(final Integer movieId) {
        try {
            List<CategoryEntity> entities = categoryRepositoryDB.getCategoriesByMovieId(movieId);
            return categoryMapper.toDto(entities);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las categorías", e);
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        try {
            List<CategoryEntity> categories = categoryRepositoryDB.findAll();
            return categoryMapper.toDto(categories);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todas las categorías", e);
        }
    }

    @Override
    public CategoryDto createCategory(final CategoryDto dto) {
        try {
            CategoryEntity entity = categoryMapper.toEntity(dto);
            CategoryEntity saved = categoryRepositoryDB.save(entity);
            return categoryMapper.toDto(saved);
        } catch (Exception e) {
            //cambiar
            throw new RuntimeException("Error al crear la categoría", e);
        }
    }
}
