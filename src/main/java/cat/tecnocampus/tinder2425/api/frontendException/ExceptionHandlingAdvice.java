package cat.tecnocampus.tinder2425.api.frontendException;

import cat.tecnocampus.tinder2425.application.exception.ProfileNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ResponseBody
    @ExceptionHandler(ProfileNotFound.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Profile not found")
    String objectNotFoundHandler(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Duplicated key. Please choose another one.")
    String objectAlreadyExists(Exception exception) {
        return "Duplicated key. Please choose another one.";
    }

}
