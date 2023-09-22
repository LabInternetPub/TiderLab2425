package cat.tecnocampus.tinder2324.application.dto.domain;

import java.time.LocalDate;

public class LikeNoOriginDTO {
    private ProfileDTO target;
    private boolean matched;
    private LocalDate creationDate;
    private LocalDate matchDate;

    public LikeNoOriginDTO() {
    }

    public String getTarget() {
        return target.getEmail();
    }

    public void setTarget(ProfileDTO target) {
        this.target = target;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }
}
