package cat.tecnocampus.tinder2425.application.dto.domain;

import java.time.LocalDate;


public record LikeSummaryDTO (String origin, String target, boolean matched, LocalDate creationDate, LocalDate matchDate)
{}
