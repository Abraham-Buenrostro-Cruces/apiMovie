package mx.badak.movies.domain.core;

import mx.badak.movies.domain.model.LoginRequestDto;
import mx.badak.movies.domain.model.LoginResponseDto;
import mx.badak.movies.domain.port.UserRepositoryDB;
import mx.badak.movies.domain.service.LoginService;
import mx.badak.movies.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepositoryDB userRepositoryDB;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(LoginRequestDto dto){

        UserEntity user = userRepositoryDB.findByUserName(dto.userName()).orElse(null);

        if (user == null)
            return new LoginResponseDto(false, "Usuario no encontrado");

        boolean matches = passwordEncoder.matches(dto.password(), user.getPassword());

        if (!matches) {
            return new LoginResponseDto(false, "Contraseña incorrecta");
        }

        return new LoginResponseDto(true, "Login exitoso");
    }
}