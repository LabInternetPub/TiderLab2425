package cat.tecnocampus.tinder2324.application.aop;

import cat.tecnocampus.tinder2324.domain.Like;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LoggerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    //Matches all methods from TinderController, returning anything and which name contains the word "Profile" and has
    // any parameter
    @Pointcut("execution (* cat.tecnocampus.tinder2324.application.TinderService.*Profile*(..))")
    public void dealingProfilePoincut() {}

    @Before("dealingProfilePoincut()")
    public void beforeDealingProfilesAdvice() {
        logger.info("Going to deal with Profiles");
    }

    @After("dealingProfilePoincut()")
    public void afterDealingProfilesAdvice() {
        logger.info("Already dealt with Profiles");
    }

    //Matches all methods in the package api which have a parameter of type Principal
    @Pointcut("within(cat.tecnocampus.tinder2324.api..*) && args(java.security.Principal)")
    public void principalParameterPointcut() {}

    @Before("principalParameterPointcut()")
    public void beforePrincipalAdvice() {
        logger.info("Dealing with parameter Principal");
    }

    //Matches all methods returning a list of Like, with any name and any parameters
    @Pointcut("execution(java.util.List<cat.tecnocampus.tinder2324.domain.Like> * (..))")
    public void likesPointcut() {}

    //Around advice. Note that this method must return what the proxied method is supposed to return
    @Around("likesPointcut()")
    public List<Like> likesAdvice(ProceedingJoinPoint jp) {

        try {
            logger.info("Before showing likes");
            List<Like> res = (List<Like>) jp.proceed();
            logger.info("Already returned a list of Likes");
            return res;
        } catch (Throwable throwable) {
            logger.info("Something went wrong when attempting to list likes");
            throwable.printStackTrace();
            return new ArrayList<Like>();
        }
    }

    //Getting the parameters of the proxied method
    @Pointcut("execution(* cat.tecnocampus.tinder2324.application.TinderService.getCandidates*(..)) && args(user)")
    public void showCandidatesPointcut(String user) {}

    @Before("showCandidatesPointcut(user)")
    public void showUserAdvice(String user) {
        logger.info("Going to show candidates for: " + user);
    }


}
