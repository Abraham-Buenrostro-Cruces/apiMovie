package mx.badak.movies.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "user_name", nullable = false, length = Constants.USER_NAME_MAX_LENGTH, unique = true)
    private String userName;

    @Column(nullable = false, length = Constants.USER_PASSWORD_MAX_LENGTH)
    private String password;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;
}
