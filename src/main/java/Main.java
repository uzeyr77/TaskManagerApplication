

import Task.Task;
import Task.TaskDAO;
import Task.TaskStatus;
import Task.Database;
import Task.TaskService;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
    Database db = new Database();
    TaskDAO taskDAO = new TaskDAO(db);
    TaskService taskService = new TaskService(taskDAO);
    taskService.createTask("New database", TaskStatus.IN_PROGRESS);
    }
}