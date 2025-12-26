package Task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message, Throwable err) {
        super(message, err);
    }
}
