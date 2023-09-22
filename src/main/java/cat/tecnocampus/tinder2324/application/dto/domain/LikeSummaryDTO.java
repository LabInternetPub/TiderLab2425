package cat.tecnocampus.tinder2324.application.dto.domain;

import java.time.LocalDate;


public record LikeSummaryDTO (String origin, String target, boolean matched, LocalDate creationDate, LocalDate matchDate)
{}
