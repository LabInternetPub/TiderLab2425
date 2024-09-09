package cat.tecnocampus.tinder2425.application.dto.domain;

import java.time.LocalDate;

public interface LikeSummaryInterface {
     boolean getMatched();

     String getOriginEmail();
    LocalDate getCreationDate();

    LocalDate getMatchDate();
}
