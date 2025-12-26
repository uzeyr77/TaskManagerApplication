package Task;

public class TaskPersistanceException extends RuntimeException {
    public TaskPersistanceException(String message, Throwable err) {
        super(message, err);
    }
}
