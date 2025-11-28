package mx.badak.movies.application;

import mx.badak.movies.domain.model.LoginRequestDto;
import mx.badak.movies.domain.model.LoginResponseDto;
import mx.badak.movies.domain.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return loginService.login(dto);
    }
}
