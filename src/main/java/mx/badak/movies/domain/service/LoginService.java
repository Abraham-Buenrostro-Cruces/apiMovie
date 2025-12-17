package mx.badak.movies.domain.service;

import mx.badak.movies.domain.model.LoginRequestDto;
import mx.badak.movies.domain.model.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto dto);
}
