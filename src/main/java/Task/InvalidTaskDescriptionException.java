package Task;

public class InvalidTaskDescriptionException extends RuntimeException {
    public InvalidTaskDescriptionException(String message) {
        super(message);
    }
}
