package Task;

import java.util.*;

public class TaskRepository {
    private HashMap<String, Task> map;

    public TaskRepository() {
        this.map = new LinkedHashMap<>();
    }
    public TaskRepository(TaskRepository tp) { this.map = new HashMap<>(tp.getMap());}
    public void addTask(Task t) {
        if(this.map.containsKey(t.getId())) System.out.println("throw duplicate task exception");
        map.put(t.getId(), t);
    }
    public Task getTask(Task t) {
        if(map.isEmpty()) System.out.println("empty map");
        return map.get(t.getId());
    }
    public Task getTask(String id) {
        if(map.isEmpty()) System.out.println("empty map");
        return map.get(id);
    }
    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }
    public boolean containsTask(Task t) {
        return this.map.containsKey(t.getId());
    }
    public boolean containsId(String id) { return this.map.containsKey(id);}
    public boolean removeTask(String id) {
        if(map.isEmpty()) throw new RuntimeException("cant remove from empty list");
        return map.remove(id).getId().equals(id);

    }
    private Map<String, Task> getMap() {
        return new HashMap<>(map); //dcopy
    }
    public Collection<Task> Tasks() {
        return map.values();
    }
    public Task findById(String id) throws Exception {
        if(map.isEmpty()) {
            System.out.println("[empty map exception]");
        }
        if(map.containsKey(id)) return map.get(id);
        throw new Exception("Task.Task does not exist");
    }
    public Collection<Task> allTasks() {
        return map.values();
    }
    public Collection<Task> getByStatus(String status) {
        Collection<Task> tasksByStatus = new ArrayList<>();
        for(Task t: map.values()) {
            if(t.getStatus().toString().equals(status)) tasksByStatus.add(t);
        }
        return tasksByStatus;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
