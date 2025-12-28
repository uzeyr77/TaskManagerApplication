package Task;

import java.util.Locale;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE,
    TBD,
    NONE;


    private TaskStatus() {

    }

    public static TaskStatus stringToTaskStatus(String status) {
        if(status.equals("in progress")) return TaskStatus.IN_PROGRESS;
        status = status.toUpperCase();
        return TaskStatus.valueOf(status);
    }
    public static String taskStatusToString(TaskStatus status) {
        if(status.equals(TaskStatus.IN_PROGRESS)) return "in progress";
        System.out.println(status.toString().toLowerCase());
        return status.toString().toLowerCase();
    }
}
