package mx.badak.movies.infrastructure.entity;

public interface MovieCategory {
    Long getId();
    String getTitle();
    String getDescription();
    Long getIdCategory();
    String getName();
}

/*
return rows.stream()
            // Agrupar por ID de película
            .collect(Collectors.groupingBy(MovieCategoryProjection::getMovieId))
            .entrySet()
            .stream()
            .map(entry -> {

                // movieId del grupo
                var movieId = entry.getKey();

                // Todas las filas correspondientes a esa película
                var group = entry.getValue();

                // Usamos la primera fila para obtener info general de la película
                var first = group.getFirst();

                // Convertimos cada fila del grupo en CategoryDto
                var categories = group.stream()
                        .map(r -> new CategoryDto(
                                r.getCategoryId(),
                                r.getCategoryName()
                        ))
                        .toList();

                // Construimos el MovieDto final
                return new MovieDto(
                        movieId,
                        first.getMovieTitle(),
                        first.getMovieDescription(),
                        first.getMovieImageUrl(),
                        first.getMovieReleaseDate(),
                        categories
                );


* */
