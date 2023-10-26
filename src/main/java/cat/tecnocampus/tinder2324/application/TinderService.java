package cat.tecnocampus.tinder2324.application;

import cat.tecnocampus.tinder2324.application.dto.domain.LikeSummaryDTO;
import cat.tecnocampus.tinder2324.application.dto.domain.LikeSummaryInterface;
import cat.tecnocampus.tinder2324.application.dto.domain.ProfileDTO;
import cat.tecnocampus.tinder2324.application.exception.ProfileNotFound;
import cat.tecnocampus.tinder2324.domain.Like;
import cat.tecnocampus.tinder2324.domain.Profile;
import cat.tecnocampus.tinder2324.persistence.LikeRepository;
import cat.tecnocampus.tinder2324.persistence.ProfileRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

@Controller
public class TinderService {
	private ProfileRepository profileRepository;
	private LikeRepository likeRepository;
	private ModelMapper modelMapper;

	public TinderService(ProfileRepository profileRepository, LikeRepository likeRepository, ModelMapper modelMapper) {
		this.profileRepository = profileRepository;
		this.likeRepository = likeRepository;
		this.modelMapper = modelMapper;
	}

	public ProfileDTO getProfile(String email) {
		var profile = profileRepository.findById(email).orElseThrow(() -> new ProfileNotFound(email));
		return modelMapper.map(profile, ProfileDTO.class);
	}

	private Profile getProfileInternal(String email) {
		return profileRepository.findById(email).orElseThrow(() -> new ProfileNotFound(email));
	}

	public ProfileDTO getProfileWithNickname(String username) {
		var profile = profileRepository.findByNickname(username).orElseThrow(() -> new ProfileNotFound(username));
		return modelMapper.map(profile, ProfileDTO.class);
	}
	private Profile getProfileWithNicknameDomain(String username) {
		return profileRepository.findByNickname(username).orElseThrow(() -> new ProfileNotFound(username));
	}


	public List<ProfileDTO> getProfiles() {
		return profileRepository.findAll().stream().map((profile) -> modelMapper.map(profile, ProfileDTO.class)).toList();
	}

	private List<Profile> getProfilesInternal() {
		return profileRepository.findAll();
	}

	public List<ProfileDTO> getCandidates(String email) {
		Profile user = this.getProfileInternal(email);
		return this.getProfilesInternal().stream()
				.filter(user::isCompatible)
				.map((profile) -> modelMapper.map(profile, ProfileDTO.class))
				.toList();
	}

	public List<ProfileDTO> getCandidatesByNickname(String username) {
		Profile user = this.getProfileWithNicknameDomain(username);
		return this.getProfilesInternal().stream()
				.filter(user::isCompatible)
				.map((profile) -> modelMapper.map(profile, ProfileDTO.class))
				.toList();
	}

	public Profile addProfile(ProfileDTO profileDTO) {
		var profile = modelMapper.map(profileDTO, Profile.class);
		return profileRepository.save(profile);
	}

	@Transactional
	public int newLikes(String originEmail, List<String> targetEmail) {
		Profile origin = profileRepository.findById(originEmail)
				.orElseThrow(() -> new ProfileNotFound(originEmail));
		List<Like> likes =
		targetEmail.stream().map(email -> profileRepository.findById(email).orElseThrow(() -> new ProfileNotFound(email)))
				.filter(origin::isCompatible) 			//make sure it is compatible
				.map(origin::createAndMatchLike)		//create likes
				.toList();
		origin.addLikes(likes);
		List<Like> updatedTargetMatchingLikes = updateTargetMatchingLikes(origin, likes);
		return likes.size();
	}

	private List<Like> updateTargetMatchingLikes(Profile origin, List<Like> likes) {
		return likes.stream().filter(Like::isMatched)
				.map(l -> likeRepository.findFirstByOriginEmailEqualsIgnoreCaseAndTargetEmailEqualsIgnoreCase(l.getTarget().getEmail(), origin.getEmail()).get())
				.map(l -> {l.setMatched(true);
					l.setMatchDate(LocalDate.now()); return l;}).toList();
	}

	public List<LikeSummaryDTO> getLikes() {
		return likeRepository.findLikeSummary();
	}

	public List<LikeSummaryInterface> getLikesSummaryInterface() {
		return likeRepository.findAllProjectedBy();
	}
}
