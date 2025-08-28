import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> tasks;
    private ArrayList<ObjectNode> jsonNodeList;
    private ObjectMapper om;
    private File file;
    public TaskManager() {
        tasks = new ArrayList<>();
        jsonNodeList = new ArrayList<>();
        om = new ObjectMapper();
        file = new File("data/info.json");
    }
    public void addTasks(Task t) {
        // call validate Task which should check if the task.description is null or empty and same
        // for the status
        // call a check to see if the task already exists
        // if the first two conditions pass then add the task to the list
        validateTask(t);
        checkTaskList(t);
        tasks.add(t);
    }

    private void checkTaskList(Task t) {
        for(Task task: tasks) {
            if(task.equals(t)) throw new RuntimeException("The task already exists");
        }
    }

    private void validateTask(Task t) {
        if(t.getDescription() == null || t.getStatus() == null) {
            throw new RuntimeException("Description or status of task cannot be null");
        }
    }

    public int findTask(int taskID) {
        if(taskID > tasks.size()) {
            return -1;
        }
        for(int i = 0; i < tasks.size();i++) {

            if(tasks.get(i).getId() == taskID) {
              return i;
            }
        }

        return -1;

    }

    public void writeToFile(File f) throws JsonProcessingException {
        //ObjectMapper om = new ObjectMapper();
        file = f;
        ObjectNode node;
        om.registerModule(new JavaTimeModule());


        for (int i = 0; i < tasks.size(); i++) {
            node = om.createObjectNode();
//            String json = om.writeValueAsString(tasks.get(i)); // serialize, task obj --> String in json form
//            Task copy = om.readValue(json, Task.class); // deserialize back, json --> task obj
            node.put("ID", tasks.get(i).getId());
            node.put("Description", tasks.get(i).getId());
            node.put("Status", tasks.get(i).getId());
            node.put("Created At", tasks.get(i).getId());
            node.put("Updated At", tasks.get(i).getId());

            jsonNodeList.add(node);
            try {

                om.writeValue(file, jsonNodeList);

            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());

            }

        }
    }

    public void delete(int id) {
        ArrayList<Task> updatedList = new ArrayList<>();
        for(int i = 0; i < this.tasks.size();i++) {
            if(tasks.get(i).getId() == id) {
                this.tasks.remove(i);
                updatedList = tasks;
                break;
            }

        }
        try {
            om.writeValue(file, updatedList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(int target, String info) {
        //ArrayList<Task> updatedList = new ArrayList<>();
        if(info == null) {
            throw new NullPointerException("The description is null");
        }
        if(this.findTask(target) == -1) {
            throw new RuntimeException("Task is invalid");
        }
        for(int i = 0; i < this.tasks.size();i++) {

            if(tasks.get(i).getId() == target) {
                tasks.get(i).setDescription(info);
                break;
                //tasks.get(i).setDescription(info);
                //tasks.get(i).updateTime();
                //updatedList = tasks;
            }
        }

        try {
            om.writeValue(file, tasks);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void updateStatus(int taskID, String status) {
        ArrayList<Task> updatedList = new ArrayList<>();
        for(int i = 0; i < tasks.size();i++) {

            if(tasks.get(i).getId() == taskID) {
                tasks.get(i).setStatus(status);
                tasks.get(i).setUpdatedAt(LocalDate.now().toString());
                break;
                //updatedList = tasks;
            }
        }

        try {
            om.writeValue(file, tasks);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void addTaskToJson(Task t) {
        // add the task to the arraylist of tasks
        tasks.add(t);

        try {
            om.writeValue(file, tasks);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


    public String listAll() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size();i++) {
            sb.append(tasks.get(i).toString());
            if(i < tasks.size()-1) sb.append(" ");

        }
        return sb.toString();
    }

    private boolean findStatus(Task t, String status) {
        return t.getStatus().equals(status);
    }

    public String listDone() {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < tasks.size();i++) {

            if(findStatus(tasks.get(i), "done")) {
                sb.append(tasks.get(i).toString());
            }
            if(i < tasks.size() - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public String listInProg() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size();i++) {
            if(findStatus(tasks.get(i), "in-progress")) {
                sb.append(tasks.get(i).toString());
            }
            if(i < tasks.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String listTodo() {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size();i++) {

            if(findStatus(tasks.get(i), "to-do")) {
                sb.append(tasks.get(i).toString());
            }
            if(i < tasks.size() - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<ObjectNode> getJsonNodeList() {
        return jsonNodeList;
    }

    public void setJsonNodeList(ArrayList<ObjectNode> jsonNodeList) {
        this.jsonNodeList = jsonNodeList;
    }

    public ObjectMapper getOm() {
        return om;
    }

    public void setOm(ObjectMapper om) {
        this.om = om;
    }
}