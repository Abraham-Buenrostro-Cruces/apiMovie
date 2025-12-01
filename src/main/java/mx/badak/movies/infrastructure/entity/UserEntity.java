package mx.badak.movies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 15, unique = true)
    private String userName;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;
}
