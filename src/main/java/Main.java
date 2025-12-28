

import Task.Task;
import Task.TaskDAO;
import Task.TaskStatus;
import Task.Database;
import Task.TaskService;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        Connection con = Task.Database.getConnection();
//
//        if(con != null) {
//            System.out.println("database connection successful");
//        }
        Database database = new Database();
        Connection con = database.getConnection();
        TaskDAO taskDAO = new TaskDAO(database);
        //Task t1 = new Task("...", TaskStatus.TODO);
//        //Task t2 = new Task("testing with data", TaskStatus.IN_PROGRESS);
//        //Task t3 = new Task("IT WOKRS", TaskStatus.IN_PROGRESS);
//        //taskDAO.insert(t2);
//        //taskDAO.insert(t1);
//        //taskDAO.updateStatus("99MC8", TaskStatus.DONE);
//        //System.out.println(taskDAO.getByDescription("..."));
//        System.out.println(taskDAO.getAllByStatus(TaskStatus.DONE));

        TaskService taskService = new TaskService(taskDAO);
        //taskService.createTask("test to see if check works", TaskStatus.TODO);
        //System.out.println(taskService.getTaskByID("QUWAT"));
//        System.out.println(taskService.getAllTasks());
//        System.out.println(taskService.getByDescription("NOT DONE"));
//        System.out.println(taskService.getTaskByID("12334"));
//        //System.out.println(taskService.updateStatus("44OUD",TaskStatus.DONE));
//        System.out.println(taskService.getTaskByID("44OUD"));
//        System.out.println(taskService.updateDescription("44OUD", "testing456"));
//        System.out.println(taskService.getByStatus(TaskStatus.DONE));
//        System.out.println(taskService.getAllTasks());
//        System.out.println(taskService.updateStatus("I7RGI",TaskStatus.DONE));
//        System.out.println("Here are all the done tasks " + taskService.getAllByStatus(TaskStatus.DONE));
//        System.out.println("here are all the to-dos " + taskService.getAllByStatus(TaskStatus.TODO));
//        System.out.println("here are all the tasks " + taskService.getAllTasks());
//        System.out.println(taskService.deleteTask("P8CL5"));
//        System.out.println(taskService.deleteTask("I7RGI"));
//        System.out.println(taskService.getAllTasks());
//        System.out.println(taskService.updateStatus("QUWAT", TaskStatus.NONE));

        //System.out.println(taskService.checkDuplicateID("1VQFH"));
        //System.out.println(taskService.createTask("get in there lad", TaskStatus.IN_PROGRESS));
        System.out.println(taskService.getAllTasks());
        System.out.println(taskService.getTaskByID("QUWAT"));
    }
}