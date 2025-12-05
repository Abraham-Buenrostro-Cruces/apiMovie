package mx.badak.movies.domain.mapper;

import mx.badak.movies.domain.model.UserDto;
import mx.badak.movies.infrastructure.entity.UserEntity;

public class UserMapper {

    public static UserDto toDto(final UserEntity u) {
        return new UserDto(
                u.getId(),
                u.getUserName(),
                u.getPassword(),
                u.getIsAdmin()
        );
    }

    public static UserEntity toEntity(final UserDto dto) {
        UserEntity u = new UserEntity();
        u.setId(dto.id());
        u.setUserName(dto.userName());
        u.setPassword(dto.password());
        u.setIsAdmin(dto.isAdmin());
        return u;
    }
}
