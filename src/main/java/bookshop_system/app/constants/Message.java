package bookshop_system.app.constants;

public class Message {

    private Message() {
    }

    //Validator
    public static final String INCORRECT_USERNAME_LENGTH = "Username must be between 4 and 30 characters.";
    public static final String INVALID_AGE_INPUT = "Please enter valid age.";
    public static final String INCORRECT_PASSWORD_LENGTH = "Password must be between 6 and 50 characters.";
    public static final String INCORRECT_PASSWORD_INPUT = "Password must contain a lowercase and, " +
            "uppercase character, 1 digit and 1 special symbol";


    //Exception
    public static final String NOT_A_NUMBER = "Not a number.";
    public static final String NO_SUCH_NUMBER = "There is no such exercise.";

    //Message
    public static final String INSTRUCTIONS = "Please select a query from 1 to 4 you wish to test.\n" +
            "If you want to end testing, press 0.";
}
