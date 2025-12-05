package mx.badak.movies.domain.core;

import mx.badak.movies.domain.mapper.UserMapper;
import mx.badak.movies.domain.model.UserDto;
import mx.badak.movies.domain.port.UserRepositoryDB;
import mx.badak.movies.domain.service.UserService;
import mx.badak.movies.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryDB repository;

    public UserServiceImpl(final UserRepositoryDB repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserDto createUser(final UserDto dto) {
        UserEntity entity = UserMapper.toEntity(dto);
        UserEntity saved = repository.save(entity);
        return UserMapper.toDto(saved);
    }

    @Override
    public void deleteUser(final Integer id) {
        repository.deleteById(id);
    }
}
