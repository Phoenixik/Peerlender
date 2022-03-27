package domain.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super("user with "+ username + "not found");
    }
}
