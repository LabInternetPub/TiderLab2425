package cat.tecnocampus.tinder2324.application.dto.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

// a dto from profile
public class ProfileDTO {
    @Email
    private String email;
    @Size(min=5, max=10)
    @Size(min=5, max=10)
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Nickname must begin with a capital letter. Also only letters are allowed")
    private String nickname;
    private String gender;
    private String attraction;
    private String passion;
    private List<LikeNoOriginDTO> likes = new ArrayList<>();

    public ProfileDTO() {
    }

    public ProfileDTO(String email, String nickname, String gender, String attraction, String passion) {
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.attraction = attraction;
        this.passion = passion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    public String getPassion() {
        return passion;
    }

    public void setPassion(String passion) {
        this.passion = passion;
    }

    public List<LikeNoOriginDTO> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeNoOriginDTO> likes) {
        this.likes = likes;
    }
}
