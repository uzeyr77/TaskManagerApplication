package Task;

import java.util.Scanner;

public class UserInterface {
    private String input;
    private TaskService taskService;
    private TaskDAO taskDAO;
    private Scanner s;
    public UserInterface(Database database) {
        taskDAO = new TaskDAO(database);
        taskService = new TaskService(taskDAO);
        s = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome!");
        while(true) {
            System.out.println("Options");
            System.out.println("1 - Add new task");
            System.out.println("2 - Delete existing task");
            System.out.println("3 - Update Status of existing task");
            System.out.println("4 - Update Description of existing task");
            System.out.println("5 - List all existing tasks");
            System.out.println("6 - List tasks based on status");
            System.out.println("7 - Delete all existing tasks");
            System.out.println("PRESS ENTER TO EXIT");
            String input = s.nextLine();
            if(input.isEmpty())
                break;
            switch (input) {
                case "1":
                    System.out.println(handleAdd());
                    break;
                case "2":
                    // call delete handler
                    System.out.println(handleDelete());
                    break;
                case "3":
                    // call update status
                    System.out.println(handleUpdateStatus());
                    break;
                case "4":
                    // call update desc
                    System.out.println(handleUpdateDescription());
                    break;
                case "5":
                    // call list all handler
                    System.out.println(handleListAll());
                    break;
                case "6":
                    // call list by status handler
                    System.out.println(handleListAllByStatus());
                    break;
                case "7":
                    // call delete all
                    System.out.println(handleDeleteAll());
                    break;
                case "":
                    System.out.println("EXITING");
                    break;
                default:
                    break;

            }
        }

    }
    public String handleAdd() {
        System.out.println("Enter description");
        String desc = s.nextLine();
        System.out.println("Pick one of the following status:");
        System.out.println("1 - Todo");
        System.out.println("2 - In progress");
        System.out.println("3 - Done");
        switch (s.nextLine()) {
            case "1":
                taskService.createTask(desc, TaskStatus.TODO);
                return "Task added successfully";
            case "2":
                taskService.createTask(desc, TaskStatus.IN_PROGRESS);
                return "Task added successfully";
            case "3":
                taskService.createTask(desc, TaskStatus.DONE);
                return "Task added successfully";
            default:
                return "Invalid status input, Task not added";
        }
    }
    public String handleDelete() {
        for(Task t: taskService.getAllTasks()) {
            System.out.println(t.toString());
        }
        System.out.print("Enter ID of task to be deleted: ");
        boolean deleted = taskService.deleteTask(s.nextLine());
        if(deleted)
            return "Task successfully deleted";
        return "Task could not be deleted";
    }
    public String handleUpdateStatus() {
        for (Task t : taskService.getAllTasks()) {
            System.out.println(t.toString());
        }
        System.out.print("Enter ID of task to update status: ");
        String id = s.nextLine();
        System.out.println("Pick one of the following status:");
        System.out.println("1 - Todo");
        System.out.println("2 - In progress");
        System.out.println("3 - Done");
        return switch (s.nextLine()) {
            case "1" -> {
                taskService.updateStatus(id, TaskStatus.TODO);
                yield "Task status updated successfully";
            }
            case "2" -> {
                taskService.updateStatus(id, TaskStatus.IN_PROGRESS);
                yield "Task status updated successfully";
            }
            case "3" -> {
                taskService.updateStatus(id, TaskStatus.DONE);
                yield "Task status updated successfully";
            }
            default -> "Invalid status input, Task not updated";
        };
    }
    public String handleUpdateDescription() {
        for (Task t : taskService.getAllTasks()) {
            System.out.println(t.toString());
        }
        System.out.print("Enter ID of task to update status: ");
        String id = s.nextLine();
        System.out.println("Enter the update");
        boolean updated = taskService.updateDescription(id, s.nextLine());
        if(updated)
            return "Task successfully updated";
        return "Invalid Description input, Task not updated";
    }
    public String handleListAll() {
        if(taskService.getAllTasks().isEmpty()) return "No tasks to be listed";
        StringBuilder result = new StringBuilder();
        for(Task t: taskService.getAllTasks()) {
            result.append(t).append("\n");
        }
        return result.toString();
    }
    public String handleListAllByStatus() {
        StringBuilder result = new StringBuilder();
        System.out.println("Select task status to list");
        System.out.println("1 - Todo");
        System.out.println("2 - In progress");
        System.out.println("3 - Done");
        return switch (s.nextLine()) {
            case "1" -> {
                if(taskService.getAllByStatus(TaskStatus.TODO).isEmpty())
                    yield  "No Tasks with status: To do";
                for(Task t: taskService.getAllByStatus(TaskStatus.TODO)) {
                    result.append(t).append("\n");
                }
                yield result.toString();
            }
            case "2" -> {
                if (taskService.getAllByStatus(TaskStatus.IN_PROGRESS).isEmpty())
                    yield "No Tasks with status: In progress";
                for (Task t : taskService.getAllByStatus(TaskStatus.IN_PROGRESS)) {
                    result.append(t).append("\n");
                }
                    yield result.toString();
            }
            case "3" -> {
                if (taskService.getAllByStatus(TaskStatus.DONE).isEmpty())
                    yield "No Tasks with status: Done";
                for (Task t : taskService.getAllByStatus(TaskStatus.DONE)) {
                    result.append(t).append("\n");
                }
                    yield result.toString();
            }
            default -> "Invalid status input, tasks not listed";
        };
    }
    public String handleDeleteAll() {
        if(taskService.getAllTasks().isEmpty()) return "no tasks to be deleted, record is empty";
        boolean b = taskService.deleteAllTasks();
        if(b)
            return "All tasks successfully deleted";
        return "Tasks not deleted";
    }
}
