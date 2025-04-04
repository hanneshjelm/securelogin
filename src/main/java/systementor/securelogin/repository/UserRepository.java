package systementor.securelogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import systementor.securelogin.model.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}