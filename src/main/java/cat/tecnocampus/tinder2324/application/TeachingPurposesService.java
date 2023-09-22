package cat.tecnocampus.tinder2324.application;

import cat.tecnocampus.tinder2324.domain.Profile;
import cat.tecnocampus.tinder2324.persistence.ProfileRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TeachingPurposesService {
    private final ProfileRepository profileRepository;

    public TeachingPurposesService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public void createTwoProfilesWithError() {
        createProfile("pepe@gmail.com", Profile.Gender.Man, "Pepeee", Profile.Passion.Dance);
        if (true) throw new RuntimeException("I want an error");
        createProfile("pepa@gmail.com", Profile.Gender.Woman, "Pepaaa", Profile.Passion.Dance);
    }

    private void createProfile(String email, Profile.Gender gender, String username, Profile.Passion passion) {
        Profile profile = new Profile();
        profile.setAttraction(gender);
        profile.setEmail(email);
        profile.setGender(gender);
        profile.setNickname(username);
        profile.setPassion(passion);

        profileRepository.save(profile);
    }
}
