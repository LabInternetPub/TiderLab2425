package cat.tecnocampus.tinder2324.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tinder_like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Profile origin;
    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonBackReference
    private Profile target;
    private boolean matched;
    private LocalDate creationDate;
    private LocalDate matchDate;

    public Like() {
    }

    public Like(Profile origin, Profile target) {
        this.origin = origin;
        this.target = target;
        creationDate = LocalDate.now();
        matched = false;
        matchDate = null;
    }

    public Profile getTarget() {
        return target;
    }

    public void setTarget(Profile target) {
        this.target = target;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
        this.matchDate = LocalDate.now();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate date) {
        this.creationDate = date;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    @Override
    public String toString() {
        return "Like{" +
                "Id=" + id +
                ", origin=" + origin +
                ", target=" + target +
                ", matched=" + matched +
                ", creationDate=" + creationDate +
                ", matchDate=" + matchDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like like)) return false;

        return id.equals(like.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
