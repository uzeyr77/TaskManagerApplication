import static org.junit.jupiter.api.Assertions.*;

import Task.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FileTaskManagerTest {
    TaskManagerImpl tm;
    Task t1;
    Task t2;
    Task t3;
    Task t4;
    Task t5;

    @BeforeEach
    void reset_TaskObj() {
    t1 = new Task("buy groceries", "to-do");
    t2 = new Task("finish project", "to-do");
    t3 = new Task("buy new shoes", "in-progress");
    t4 = new Task("buy a sweater", "in-progress");
    t5 = new Task("read book", "done");

    tm = new TaskManagerImpl();
    }

    // TESTING VALID OPERATIONS

    @Test
    void addTask_validTask_shouldReturnTrue() {
        tm.addTask(t1);
        assertEquals(t1,tm.getTasks().getFirst());
    }

    @Test
    void addTask_multipleValidTasks_shouldReturnTrue() {
        tm.addTask(t1);
        tm.addTask(t2);
        assertEquals(t1, tm.getTasks().getFirst());
        assertEquals(t2, tm.getTasks().get(0));

    }
    // add task with dupe
    @Test
    void findTask_validTask_shouldReturnTrue() {
        tm.addTask(t1);
        int index = tm.findTask(t1.getId());
        tm.getTasks().get(index).getId();
        assertEquals(t1.getId(), tm.getTasks().get(index).getId());
    }
    @Test
    void findTask_multipleValidTasks_shouldReturnTrue() {
        tm.addTask(t1);
        tm.addTask(t2);
        int index = tm.findTask(t2.getId());
        assertEquals(t1.getId(), tm.getTasks().get(tm.findTask(t1.getId())).getId());
        assertEquals(t2.getId(), tm.getTasks().get(index).getId());
        Object task = new Task();
    }
    @Test
    void deleteTask_validTaskID_shouldReturnTrue() {
        tm.deleteTask(t1.getId());
        int loc = tm.findTask(t1.getId());
        assertEquals(-1, loc);
    }
    @Test
    void deleteTask_invalidTaskId_shouldReturnTrue() {
        tm.deleteTask(3);
        int loc = tm.findTask(3);
        assertEquals(-1, loc);
    }
    @Test
    void deleteTask_multipleValidIds_shouldReturnTrue() {
        tm.deleteTask(t1.getId());
        tm.deleteTask(t2.getId());
        int loc1 = tm.findTask(t1.getId());
        int loc2 = tm.findTask(t2.getId());
        assertEquals(-1,loc1);
        assertEquals(-1,loc2);
    }
    @Test
    void update_validTaskIDAndValidUpdate_shouldReturnTrue() {
        tm.addTask(t1);
       // System.out.println(t1.getId());
       // System.out.println(t1.getDescription());
        tm.update(t1.getId(), "buy groceries and cleaning products");
        //System.out.println(t1.getDescription());
        assertEquals("buy groceries and cleaning products", t1.getDescription());
    }

    @Test
    void update_validTaskIdAndInvalidInfo_shouldReturnTrue() {
        tm.addTask(t2);
        //tm.update(t2.getId(), null);
        assertThrows(NullPointerException.class, () -> {
            tm.update(t2.getId(), null);
        });
    }

    @Test
    void update_invalidTaskIDAndValidInfo_shouldReturnTrue() {
        tm.addTask(t2);
        assertThrows(RuntimeException.class, () -> {
            tm.update(2, "go for bike ride");
        }); // there is no task with the id 2
    }

    @Test
    void update_invalidtaskIDAndInfo_shouldReturnTrue() {
        assertThrows(NullPointerException.class, () -> {
            tm.update(4, null);
        });
    }

    @Test
    void listAll_validTasks_shouldReturnTrue() {
        tm.addTask(t1); tm.addTask(t2); tm.addTask(t3); tm.addTask(t4); tm.addTask(t5);
        String list = tm.listAll();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< tm.getTasks().size();i++) {
            sb.append(tm.getTasks().get(i));
            if(i < tm.getTasks().size() -1) {
                sb.append(" ");
            }
        }

        assertEquals(sb.toString(), tm.listAll());
    }

    @Test
    void listInProg_validTasks_shouldReturnTrue() {
    tm.addTask(t1); tm.addTask(t2); tm.addTask(t3); tm.addTask(t4); tm.addTask(t5);
    String list = tm.listAll();
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> taskList = tm.getTasks();
        for(int i = 0; i< taskList.size();i++) {
            if(taskList.get(i).getStatus().equals("in-progress")) {
                sb.append(tm.getTasks().get(i));
            }
                if (i < tm.getTasks().size() - 1) {
                    sb.append(" ");
                }
        }

        assertEquals(sb.toString(), tm.listInProg());

    }

    @Test
    void listTodo_ValidTasks_shouldReturnTrue() {
        tm.addTask(t1); tm.addTask(t2); tm.addTask(t3); tm.addTask(t4); tm.addTask(t5);
        String list = tm.listAll();
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> taskList = tm.getTasks();
        for(int i = 0; i< taskList.size();i++) {
            if(taskList.get(i).getStatus().equals("to-do")) {
                sb.append(tm.getTasks().get(i));
            }
            if (i < tm.getTasks().size() - 1) {
                sb.append(" ");
            }
        }

        assertEquals(sb.toString(), tm.listTodo());
    }

    @Test
    void listDone_validTasks_shouldReturnTrue() {
        tm.addTask(t1); tm.addTask(t2); tm.addTask(t3); tm.addTask(t4); tm.addTask(t5);
        String list = tm.listAll();
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> taskList = tm.getTasks();
        for(int i = 0; i< taskList.size();i++) {
            if(taskList.get(i).getStatus().equals("done")) {
                sb.append(tm.getTasks().get(i));
            }
            if (i < tm.getTasks().size() - 1) {
                sb.append(" ");
            }
        }

        assertEquals(sb.toString(), tm.listDone());
    }


    @Test
    void writeToFile_shouldReturnTrue() throws IOException {
        tm.addTask(t1);
        ObjectMapper om = new ObjectMapper();

        // READING
        // Logic flow is:
        // 1. create a map that contains the properties(ID, description etc) as the Key and the values
        // are the give by task1.getID() and so on. String --> Object
        Map<String, Object> tasks = new HashMap<>(); // maps ID --> Task.Task.getID()
        List<Map<String, Object>> originalCopy = new ArrayList<>(); // This list is to contain the Map
        tasks.put("ID", t1.getId());
        tasks.put("Description", t1.getDescription());
        tasks.put("Task.TaskStatus", t1.getStatus());
        tasks.put("Created At", t1.getCreatedAt());
        //tasks.put("Updated At", task1.getUpdatedAt());
        // read to the file
        File file = new File("data/writetest.json");
        try {
            tm.writeToFile(file);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        //2. create a List<Map<String, Objects>> in order to contain the Map of the json objects// add the map to the list
        originalCopy.add(tasks);

        // 3. now we read back the file
        List<Map<String, Object>> fromFile = om.readValue(file, new TypeReference<List<Map<String, Object>>>() {
        });



        //assertEquals(originalCopy, fromFile);
        assertTrue(originalCopy.equals(fromFile));

    }

    @Test

    void writeToFile_manyTasks_shouldReturnTrue() throws IOException {
        tm.addTask(t1); tm.addTask(t2); tm.addTask(t3);
        ObjectMapper om = new ObjectMapper();
        File file = new File("data/writetest.json");
        Map<String, Object> mapOfTasks = new HashMap<>();
        List<Map<String, Object>> originalCopy = new ArrayList<>();

        mapOfTasks.put("ID", t1.getId());
        mapOfTasks.put("Description", t1.getDescription());
        mapOfTasks.put("Task.TaskStatus", t1.getStatus());
        mapOfTasks.put("Created At", t1.getCreatedAt());

        mapOfTasks.put("ID", t2.getId());
        mapOfTasks.put("Description", t2.getDescription());
        mapOfTasks.put("Task.TaskStatus", t2.getStatus());
        mapOfTasks.put("Created At", t2.getCreatedAt());

        mapOfTasks.put("ID", t3.getId());
        mapOfTasks.put("Description", t3.getDescription());
        mapOfTasks.put("Task.TaskStatus", t3.getStatus());
        mapOfTasks.put("Created At", t3.getCreatedAt());

        originalCopy.add(mapOfTasks);

        try {
            tm.writeToFile(file);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<Map<String, Object>> dataFromFile = om.readValue(file, new TypeReference<List<Map<String, Object>>>() {
        });



        assertTrue(originalCopy.equals(dataFromFile));

    }

    @Test
    void addTask_duplicateTask_shouldReturnFalse() {
        tm.addTask(t1);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            tm.addTask(t1);
        });

        String expectedMsg = "The task already exists";
        String actualMsg = exception.getMessage();

        assertTrue(expectedMsg.equals(actualMsg));

         // added duplicate tasks



        //
        //
        // assertTrue(originalCopy.equals(dataFromFile));





    }


}

