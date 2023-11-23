package cat.tecnocampus.tinder2324.api;

import cat.tecnocampus.tinder2324.api.frontendException.IncorrectRESTParameter;
import cat.tecnocampus.tinder2324.application.TeachingPurposesService;
import cat.tecnocampus.tinder2324.application.TinderService;
import cat.tecnocampus.tinder2324.application.dto.domain.ProfileDTO;
import cat.tecnocampus.tinder2324.domain.Profile;
import cat.tecnocampus.tinder2324.persistence.ProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Max;

import java.security.Principal;

@RestController
@RequestMapping("/teaching")
@Validated
public class TeachingPourposesRestController {
    private final TinderService tinderService;

    ProfileRepository profileRepository;
    private final TeachingPurposesService teachingPurposesServiceCreatedHere;

    private final TeachingPurposesService teachingPurposesServiceInjected;

    public TeachingPourposesRestController(TinderService tinderService, TeachingPurposesService teachingPurposesServiceInjected,
                                           ProfileRepository profileRepository) {
        this.tinderService = tinderService;
        this.teachingPurposesServiceInjected = teachingPurposesServiceInjected;
        this.teachingPurposesServiceCreatedHere = new TeachingPurposesService(profileRepository);
    }

    @GetMapping("/int/{i}")
    public int getInt(@PathVariable @Max(50) int i) {
        return i;
    }

    @GetMapping("/unregistered")
    public String method(@CurrentSecurityContext SecurityContext context) {
        return context.getAuthentication().getName();
    }

    @GetMapping("/principal")
    public String principal(Principal principal) {
        return principal.getName();
    }


    @PostMapping("/profilesString")
    public String addProfile(@RequestBody String profile) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ProfileDTO profileObj = objectMapper.readValue(profile, ProfileDTO.class);

        Profile responseObj = tinderService.addProfile(profileObj);

        return objectMapper.writeValueAsString(responseObj);
    }

    @GetMapping("/salutation")
    public String getSalutation(@RequestParam(defaultValue = "Anonymous") String name) {
        if (name.equalsIgnoreCase("exception"))
            throw new IncorrectRESTParameter("name", name);
        return "You must be the great " + name;
    }

    @GetMapping("/createTwoProfilesOneCreated")
    public void createTwoProfilesOneCreated() {
        teachingPurposesServiceCreatedHere.createTwoProfilesWithError();
    }

    @GetMapping("/createTwoProfilesNoneCreated")
    public void createTwoProfilesNoneCreated() {
        teachingPurposesServiceInjected.createTwoProfilesWithError();
    }

}
