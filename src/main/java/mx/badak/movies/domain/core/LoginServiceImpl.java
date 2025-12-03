package mx.badak.movies.domain.core;

import mx.badak.movies.domain.model.LoginRequestDto;
import mx.badak.movies.domain.model.LoginResponseDto;
import mx.badak.movies.domain.port.UserRepositoryDB;
import mx.badak.movies.domain.service.LoginService;
import mx.badak.movies.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static mx.badak.movies.utils.Constants.SUCCESS;
import static mx.badak.movies.utils.Constants.USER_NOT_FOUND;
import static mx.badak.movies.utils.Constants.PASSWORD_NOT_FOUND;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepositoryDB userRepositoryDB;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(final LoginRequestDto dto) {

        UserEntity user = userRepositoryDB.findByUserName(dto.userName()).orElse(null);

        if (user == null) {
            return new LoginResponseDto(false, USER_NOT_FOUND, null, null);
        }

        boolean matches = passwordEncoder.matches(dto.password(), user.getPassword());

        if (!matches) {
            return new LoginResponseDto(false, PASSWORD_NOT_FOUND, null, null);
        }

        return new LoginResponseDto(true, SUCCESS, user.getUserName(), user.getIsAdmin());
    }

}
