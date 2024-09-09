package cat.tecnocampus.tinder2425.persistence;

import cat.tecnocampus.tinder2425.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByNickname(String username);
}
