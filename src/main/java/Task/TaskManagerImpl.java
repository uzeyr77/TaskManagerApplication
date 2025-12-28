package Task;

import java.time.LocalDate;


public class TaskManagerImpl implements TaskManager {
    TaskRepository tasks;
    public TaskManagerImpl() {
        tasks = new TaskRepository();
    }
    public Task getTask(String id) {
        return new Task (tasks.getTask(id));
    }

    public void addTask(Task t) {
        tasks.addTask(t);
    }
    public void deleteTask(String id) {
        tasks.removeTask(id);
    }
    public void updateDescription(String id, String info) {
        if (info == null) {
            throw new NullPointerException("The description is null");
        }
        if (!tasks.containsId(id)) {
            throw new RuntimeException("Task.Task is invalid");
        }
        for (Task task : tasks.allTasks()) {
            if (task.getId().equals(id)) {
                tasks.getTask(id).setDescription(info);
                break;
            }
        }
    }
    public void updateStatus(String taskID, TaskStatus status) {
        for (Task task : tasks.allTasks()) {
            if (task.getId().equals(taskID)) {
                task.setStatus(status);
                task.setDateUpdated(LocalDate.now());
                break;
            }
        }

    }
    public String listAll() {
        if(tasks.isEmpty()) throw new RuntimeException("No tasks to list");
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks.allTasks()) {
            result.append(task.toString());
            count++;
            if (count > 1 && count <= tasks.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    public String listDone() {
        if(tasks.isEmpty()) throw new RuntimeException("No tasks to list");
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks.getByStatus("Done")) {
            result.append(task.toString());
            count++;
            if (count > 1 && count <= tasks.size() - 1) {
                result.append(", ");
            }
        }

        return result.toString();
    }

    public String listInProg() {
        if(tasks.isEmpty()) throw new RuntimeException("No tasks to list");
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks.getByStatus("in-progress")) {
            result.append(task.toString());
            count++;
            if (count > 1 && count < tasks.size()) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    public String listTodo() {
        if(tasks.isEmpty()) throw new RuntimeException("No tasks to list");
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks.getByStatus("to-do")) {
            result.append(task.toString());
            count++;
            if (count > 1 && count < tasks.size()) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    public static void main (String [] args) {

    }
}