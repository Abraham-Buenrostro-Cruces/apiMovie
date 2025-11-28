package mx.badak.movies.domain.core;

import mx.badak.movies.domain.model.LoginRequestDto;
import mx.badak.movies.domain.model.LoginResponseDto;
import mx.badak.movies.domain.port.UserRepositoryDB;
import mx.badak.movies.domain.service.LoginService;
import mx.badak.movies.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepositoryDB userRepositoryDB;

    @Override
    public LoginResponseDto login(LoginRequestDto dto){

        UserEntity user = userRepositoryDB.findAll()
                .stream()
                .filter(u -> u.getUserName().equals(dto.userName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(dto.password())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new LoginResponseDto(
                user.getId(),
                user.getUserName(),
                user.getIsAdmin()
        );
    }
}