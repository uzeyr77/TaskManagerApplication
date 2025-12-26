package Task;

public interface TaskManager {

//    public void saveTasks() throws IOException;
    public void addTask(Task t);
    //public void checkTaskList(Task.Task t);
//    public boolean findTask(String i);
    public void deleteTask(String i);
    public void updateDescription(String id, String info);
    public void updateStatus(String id, TaskStatus status);
    public String listAll();
    public String listDone();
    public String listInProg();
    public String listTodo();
    //public Map<String, Task.Task> getTasks();
    //public void setTasks(ArrayList<Task.Task> tasks);

}
