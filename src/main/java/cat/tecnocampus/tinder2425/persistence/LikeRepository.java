package cat.tecnocampus.tinder2425.persistence;

import cat.tecnocampus.tinder2425.application.dto.domain.LikeSummaryDTO;
import cat.tecnocampus.tinder2425.application.dto.domain.LikeSummaryInterface;
import cat.tecnocampus.tinder2425.domain.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends Repository<Like, Long> {
    @Query("""
            select l from Like l where upper(l.origin.email) = upper(:email) 
            and upper(l.target.email) = upper(:email1)""")
    Optional<Like> findLikeByEmails(@Param("email") String email, @Param("email1") String email1);

   Optional<Like> findFirstByOriginEmailEqualsIgnoreCaseAndTargetEmailEqualsIgnoreCase(String origin, String target);


   @Query("""
        select new cat.tecnocampus.tinder2425.application.dto.domain.LikeSummaryDTO(
            l.origin.email,
            l.target.email ,
            l.matched ,
            l.creationDate,
            l.matchDate 
            )
            from Like l""")
   List<LikeSummaryDTO> findLikeSummary();

   List<LikeSummaryInterface> findAllProjectedBy();

}
