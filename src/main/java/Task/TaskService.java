package Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TaskService {
    private TaskDAO taskDAO;
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;

    }
    public String generateId()  {
        StringBuilder uniqueId = new StringBuilder();
        Random random = new Random();
        final String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        do {
            uniqueId.delete(0, uniqueId.length()); // reset
            for (int i = 0; i < 5; i++) {
                uniqueId.append(alphabet.charAt(random.nextInt(0, 36)));
            }
        } while(checkDuplicateID(uniqueId.toString()));

        return uniqueId.toString();
    }
    public boolean createTask(String description,TaskStatus status) throws InvalidStatusException, InvalidTaskDescriptionException{
        // validate task first
        if(validDescription(description)) throw new InvalidTaskDescriptionException("Not a valid description: " + description);
        if(status == null) throw new InvalidStatusException("Not a valid status: " + null);
        String id = this.generateId();
        Task task = new Task(id, description, status);
        try {
            return taskDAO.save(task);
        } catch (SQLException e) {
            throw new TaskPersistenceException("Failed to save task: "+ task.toString(), e);
        }
    }
    public boolean deleteTask(String id) {
        if(this.validId(id)) throw new InvalidTaskIdException("Not a valid ID: " + id);
        try {
            return taskDAO.delete(id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete task with id: " + id, e);
        }
    }
    public Optional<Task> getTaskByID(String id) {
        if(this.validId(id)) throw new InvalidTaskIdException("Not a valid ID: " + id);
        try {
            return taskDAO.get(id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve task with ID: " + id, e);
        }


    }
    public List<Task> getAllTasks() {
        try {
            return taskDAO.findAll();
        } catch(SQLException e) {
            throw new DataAccessException("Failed to retrieve all tasks", e);
        }

    }
    public Optional<Task> getByDescription(String description) {
        if(this.validDescription(description)) throw new InvalidTaskDescriptionException("The description is not valid: " + description);
        try {
            return taskDAO.getByDescription(description);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve task by description: " + description, e);
        }
    }

    public boolean updateDescription(String id, String desc) {
        if(this.validId(id)) throw new InvalidTaskIdException("The id is not valid: " + id);
        if(this.validDescription(desc)) throw new InvalidTaskDescriptionException("The description is not valid: " + desc);
        try {
            return taskDAO.update(id, desc);
        } catch(SQLException e) {
            throw new DataAccessException("Failed to update description of task with ID: " + id, e);
        }
    }

    public boolean updateStatus(String id, TaskStatus status) {
        if(this.validId(id)) throw new InvalidTaskIdException("The id is not valid: " + id);
        if(status == null) throw new InvalidStatusException("The status is not valid: " + null);
        try {
            return taskDAO.update(id, status);
        } catch(SQLException e) {
            throw new DataAccessException("Failed to update status of task with ID: " + id, e);
        }
    }

    public List<Task> getAllByStatus(TaskStatus status) throws InvalidStatusException {
        if(status == null) throw new InvalidStatusException("The status is not valid: " + null);
        try {
            return taskDAO.getAllByStatus(status);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to get all tasks with status: " + status, e);
        }
    }

    public boolean checkDuplicateID(String id) {
        if(validId(id)) throw new InvalidTaskIdException("The id is not valid: " + id);
        try {
            return taskDAO.checkById(id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to check record for Id: " + id, e);
        }
    }

    public boolean deleteAllTasks() {
        try {
            return taskDAO.deleteAll();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete all tasks",e);
        }
    }
    private boolean validDescription(String desc) {
        return desc == null || desc.length() < 5;
    }
    private boolean validId(String id) {
        return id == null || id.length() < 5;

    }

}
