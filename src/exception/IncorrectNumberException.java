package exception;

public class IncorrectNumberException extends Exception {
    public IncorrectNumberException(String message) {
        super(message);
    }

    public IncorrectNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
