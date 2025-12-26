package Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    public static boolean ISVALID;
    private static List<String> taskIds = new ArrayList<>();
    private TaskDAO taskDAO;
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;

    }

    public static String setTaskId() {
        String id = TaskIDGenerator.generateID();
        if(!taskIds.isEmpty() && taskIds.contains(id)) throw new RuntimeException("ID cannot be set, the ID already exists");
        return id;
    }
    public boolean insertTask(Task task) {
        // validate task first
        try {
            return taskDAO.save(task);
        } catch (SQLException e) {
            throw new TaskPersistanceException("Cannot save Task: "+ task.toString(), e);
        }
    }
    public boolean deleteTask(String id) {
        if(id == null || id.length() < 5) throw new InvalidTaskIdException("The id: " + id + " is not valid");
        try {
            return taskDAO.delete(id);
        } catch (SQLException e) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found", e);
        }
    }
    public Task getTaskByID(String id) {
        if(id == null || id.length() < 5) throw new InvalidTaskIdException("The id: " + id + " is not valid");
        try {
            return taskDAO.getByID(id);
        } catch (SQLException e) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found", e);
        }


    }
    public Task getTaskByDescription(String desc) {
        if(desc == null || desc.length() < 15) throw new InvalidTaskDescriptionException("Task description: " + desc + " is too short");
        try {
            return taskDAO.getByDescription(desc);
        } catch(SQLException e) {
            throw new TaskNotFoundException("Task with Description: " + desc + " not found", e);
        }

    }
    public List<Task> getAllTasks() {
        try {
            return taskDAO.getAll();
        } catch(SQLException e) {
            throw new TaskNotFoundException("Tasks not found", e);
        }

    }
    public List<Task> getByStatus(TaskStatus status) {
        if(status == null) throw new InvalidStatusException("Null Status Not Accepted");
        try {
            return taskDAO.getAllByStatus(status);
        } catch (SQLException e) {
            throw new TaskNotFoundException("Task(s) with Status: " + status.toString().replace("_","") + " not found",e);
        }
    }

    public boolean updateDescription(String id, String desc) {
        if(id == null || id.length() < 5) throw new InvalidTaskIdException("The id: " + id + " is not valid");
        else if(desc == null || desc.length() < 15) throw new InvalidTaskDescriptionException("Task description: " + desc + " is too short");
        try {
            return taskDAO.update(id, desc);
        } catch(SQLException e) {
            throw new TaskNotFoundException("Task with ID: " + id + "could not be updated",e);
        }
    }

    public boolean updateStatus(String id, TaskStatus status) {
        if(id == null || id.length() < 5) throw new InvalidTaskIdException("The id: " + id + " is not valid");
        else if(status == null) throw new InvalidStatusException("Null Status Not Accepted");
        try {
            return taskDAO.update(id, status);
        } catch(SQLException e) {
            throw new TaskNotFoundException("Task with ID: " + id + "could not be updated", e);
        }
    }

    public List<Task> getAllByStatus(TaskStatus status) throws InvalidStatusException {
        if(status == null) throw new InvalidStatusException("Null Status Not Accepted");
        try {
            return taskDAO.getAllByStatus(status);
        } catch (SQLException e) {
            throw new TaskNotFoundException("Tasks with Status: " + status.toString() + " could not be found", e);
        }
    }

    private boolean validDescription(String desc) {
        return false;
    }
    private boolean validStatus(TaskStatus status) {
        return false;
    }
    private boolean validId(String id) {
        return false;
    }
}
/*
* public static void validateTask(Task t) {
        if(t.getDescription() == null || t.getStatus() == null) {
            throw new RuntimeException("Description or status of task cannot be null");
        }
    }
    *
    * public static void checkDuplicates(Task t, TaskRepository tp) {
        if (!tp.isEmpty() && tp.containsTask(t)) {
            throw new RuntimeException("Task.Task already exists");
        }
    }
    * */