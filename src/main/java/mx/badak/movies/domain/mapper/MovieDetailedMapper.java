package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.CategoryDto;
import mx.badak.movies.domain.model.MovieDetailedDto;
import mx.badak.movies.infrastructure.entity.MovieEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieDetailedMapper {
    MovieDetailedDto toDto(MovieEntity movie, List<CategoryDto> genres);
}
