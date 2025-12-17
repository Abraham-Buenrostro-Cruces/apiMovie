package mx.badak.movies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mx.badak.movies.utils.Constants;

@Entity
@Table(name = "user_")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", nullable = false, length = Constants.USERNAME_MAX_LENGTH, unique = true)
    private String userName;

    @Column(nullable = false, length = Constants.PASSWORD_MAX_LENGTH)
    private String password;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;
}
