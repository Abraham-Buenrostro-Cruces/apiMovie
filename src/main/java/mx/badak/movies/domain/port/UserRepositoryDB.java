package mx.badak.movies.domain.port;

import mx.badak.movies.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryDB extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserName(String userName);

}
