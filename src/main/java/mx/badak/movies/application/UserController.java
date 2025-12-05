package mx.badak.movies.application;

import mx.badak.movies.domain.model.UserDto;
import mx.badak.movies.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener usuarios
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    // Registrar usuario
    @PostMapping
    public UserDto createUser(@RequestBody final UserDto dto) {
        return userService.createUser(dto);
    }

    // Borrar usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final Integer id) {
        userService.deleteUser(id);
    }
}
