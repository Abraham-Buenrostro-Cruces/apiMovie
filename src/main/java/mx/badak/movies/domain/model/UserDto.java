package mx.badak.movies.domain.model;

public record UserDto(
        Integer id,
        String userName,
        String password,
        Boolean isAdmin
) { }
