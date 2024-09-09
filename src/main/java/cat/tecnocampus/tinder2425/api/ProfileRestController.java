package cat.tecnocampus.tinder2425.api;

import cat.tecnocampus.tinder2425.application.TinderService;
import cat.tecnocampus.tinder2425.application.dto.domain.LikeSummaryDTO;
import cat.tecnocampus.tinder2425.application.dto.domain.LikeSummaryInterface;
import cat.tecnocampus.tinder2425.application.dto.domain.ProfileDTO;
import cat.tecnocampus.tinder2425.domain.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;


@RestController
public class ProfileRestController {

	private final TinderService tinderService;

	public ProfileRestController(TinderService tinderService) {
		this.tinderService = tinderService;
	}

	@PostMapping("/pepito")
	public void authenticate() {
		System.out.println("Authenticationgggggggg  .................................");
	}

	@GetMapping("/profiles/{email}")
	public ProfileDTO getProfile(@PathVariable String email)  {
		return tinderService.getProfile(email);
	}

	@GetMapping("/profiles")
	public List<ProfileDTO> getProfiles() {
		return tinderService.getProfiles();
	}

	@GetMapping("/likes")
	public List<LikeSummaryDTO> getLikes() {
		return tinderService.getLikes();
	}

	@GetMapping("/likesInterface")
	public List<LikeSummaryInterface> getLikesSummaryInterface() {
		return tinderService.getLikesSummaryInterface();
	}

	//Returns profiles that match the user (email) preferences
	@GetMapping("/profiles/{email}/candidates")
	public List<ProfileDTO> getCandidates(@PathVariable String email) {
		return tinderService.getCandidates(email);
	}

	@PostMapping("/profiles")
	@ResponseStatus(HttpStatus.CREATED)
	public Profile addProfile(@RequestBody @Valid ProfileDTO profile) {
		return tinderService.addProfile(profile);
	}

	@PostMapping("/likes")
	@ResponseStatus(HttpStatus.CREATED)
	public void addLikes(@RequestBody LikeFront likes) {
		tinderService.newLikes(likes.getOrigin(), likes.getTargets());
	}

	@GetMapping("/profiles/me")
	public ProfileDTO getProfileMe(Principal principal) {
		return tinderService.getProfileWithNickname(principal.getName());
	}

	//Returns profiles that match the registered username preferences
	@GetMapping("/profiles/me/candidates")
	public List<ProfileDTO> getCandidatesByNickname(Principal principal) {
		return tinderService.getCandidatesByNickname(principal.getName());
	}




	public static class LikeFront {
		private String origin;
		private List<String> targets;

		public LikeFront() {}
		public String getOrigin() {
			return origin;
		}
		public void setOrigin(String origin) {
			this.origin = origin;
		}
		public List<String> getTargets() {
			return targets;
		}
		public void setTargets(List<String> targets) {
			this.targets = targets;
		}
	}
}
