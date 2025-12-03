package mx.badak.movies.domain.model;

public record LoginRequestDto(
        String userName,
        String password
) {

}
