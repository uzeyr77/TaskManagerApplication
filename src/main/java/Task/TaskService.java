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
    public boolean createTask(String description,TaskStatus status) {
        // validate task first
        this.validDescription(description);
        this.validStatus(status);
        String id = this.generateId();
        Task task = new Task(id, description, status);
        try {
            return taskDAO.save(task);
        } catch (SQLException e) {
            throw new TaskPersistenceException("Failed to save task: "+ task.toString(), e);
        }
    }
    public boolean deleteTask(String id) {
        this.validId(id);
        try {
            return taskDAO.delete(id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete task with id: " + id, e);
        }
    }
    public Optional<Task> getTaskByID(String id) {
        this.validId(id);
        try {
            return taskDAO.get(id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve task with ID: " + id, e);
        }


    }
    public Optional<Task> getTaskByDescription(String desc) {
        this.validDescription(desc);
        try {
            return taskDAO.getByDescription(desc);
        } catch(SQLException e) {
            throw new DataAccessException("Failed to retrieve task with Description: " + desc, e);
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
        this.validDescription(description);
        try {
            return taskDAO.getByDescription(description);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve task by description: " + description, e);
        }
    }

    public boolean updateDescription(String id, String desc) {
        this.validId(id);
        this.validDescription(desc);
        try {
            return taskDAO.update(id, desc);
        } catch(SQLException e) {
            throw new DataAccessException("Failed to update description of task with ID: " + id, e);
        }
    }

    public boolean updateStatus(String id, TaskStatus status) {
        this.validId(id);
        this.validStatus(status);
        try {
            return taskDAO.update(id, status);
        } catch(SQLException e) {
            throw new DataAccessException("Failed to update status of task with ID: " + id, e);
        }
    }

    public List<Task> getAllByStatus(TaskStatus status) throws InvalidStatusException {
        validStatus(status);
        try {
            return taskDAO.getAllByStatus(status);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to get all tasks with status: " + status, e);
        }
    }

    public boolean checkDuplicateID(String id) {
        this.validId(id);
        try {
            return taskDAO.checkById(id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find task with ID: " + id, e);
        }
    }

    private void validDescription(String desc) {
        if(desc == null || desc.length() < 5) throw new InvalidTaskDescriptionException("The description: " + desc + " is not valid");
    }
    private void validStatus(TaskStatus status) {
        if(status == null) throw new InvalidStatusException("The null status is not valid");
    }
    private void validId(String id) {
        if(id == null || id.length() < 5) throw new InvalidTaskIdException("The id: " + id + " is not valid");

    }

}
