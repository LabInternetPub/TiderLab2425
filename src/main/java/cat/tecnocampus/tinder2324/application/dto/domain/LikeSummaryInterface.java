package cat.tecnocampus.tinder2324.application.dto.domain;

import java.time.LocalDate;

public interface LikeSummaryInterface {
     boolean getMatched();

     String getOriginEmail();
    LocalDate getCreationDate();

    LocalDate getMatchDate();
}
