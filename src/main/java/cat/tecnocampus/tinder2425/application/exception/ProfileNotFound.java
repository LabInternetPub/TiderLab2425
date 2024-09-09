package cat.tecnocampus.tinder2425.application.exception;

public class ProfileNotFound extends RuntimeException{
    public ProfileNotFound(String email)  {
        super("User " + email + " does not exist");
    }
}
