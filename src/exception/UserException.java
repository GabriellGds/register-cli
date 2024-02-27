package exception;

public class UserException extends RuntimeException{
    public UserException(String message) {
        super(message);
    }

    public static final String INVALID_EMAIL = "The email must contain @";

    public static final String INVALID_AGE = "The user must be over 18 years old";

    public static final String INVALID_HEIGHT = "The height must be a number with commas";

    public static final String DUPLICATE_EMAIL = "There is already a user with this email";

    public static final String USER_NOT_FOUND = "User not found";

    public static final String EMAIL_NOT_FOUND = "Email not found";
}
