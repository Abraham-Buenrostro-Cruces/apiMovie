package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryDB extends JpaRepository<UserEntity, Integer> {

}
