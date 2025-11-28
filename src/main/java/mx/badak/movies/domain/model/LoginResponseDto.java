package mx.badak.movies.domain.model;

public record LoginResponseDto(
    Integer id,
    String userName,
    Boolean isAdmin
){}
