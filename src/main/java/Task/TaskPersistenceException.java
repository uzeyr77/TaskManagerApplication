package Task;

public class TaskPersistenceException extends RuntimeException {
    public TaskPersistenceException(String message, Throwable err) {
        super(message, err);
    }
}
