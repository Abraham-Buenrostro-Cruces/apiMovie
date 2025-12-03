package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.infrastructure.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    List<CategoryDto> toDto(List<CategoryEntity> categories);
}
