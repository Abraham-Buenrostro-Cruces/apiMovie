package mx.badak.movies.domain.service;

import mx.badak.movies.domain.model.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto dto);

    void deleteUser(Integer id);

    UserDto getUserByUsername(String username);

}
