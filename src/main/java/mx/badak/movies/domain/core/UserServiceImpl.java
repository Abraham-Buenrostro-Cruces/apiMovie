package mx.badak.movies.domain.core;

import mx.badak.movies.domain.mapper.UserMapper;
import mx.badak.movies.domain.model.UserDto;
import mx.badak.movies.domain.port.UserRepositoryDB;
import mx.badak.movies.domain.service.UserService;
import mx.badak.movies.infrastructure.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryDB repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryDB repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserDto createUser(UserDto dto) {
        UserEntity entity = UserMapper.toEntity(dto);

        String encodedPassword = passwordEncoder.encode(dto.password());
        entity.setPassword(encodedPassword);

        UserEntity saved = repository.save(entity);
        return UserMapper.toDto(saved);
    }

    @Override
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
}
