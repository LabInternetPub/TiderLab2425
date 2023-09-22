package cat.tecnocampus.tinder2324.persistence;

import cat.tecnocampus.tinder2324.domain.UserLab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLabRepository extends JpaRepository<UserLab, Long> {
    Optional<UserLab> findByUsername(String email);
}
