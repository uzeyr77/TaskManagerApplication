package test;
import Task.*;
import Task.Database;
import Task.Task;
import Task.TaskDAO;
import Task.TaskService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    Database database = new Database();
    TaskDAO taskDAO = new TaskDAO(database);
    TaskService taskService = new TaskService(taskDAO);
    String description1;
    String description2;
    @BeforeEach
    void setUp() {
    //Task task1 = new Task("3012T", "testing task service operations", TaskStatus.IN_PROGRESS);
    //Task task2 = new Task("ABCDE", "test other operations", TaskStatus.TODO);
    description1 = "testing task service operations";
    description2 = "test other operations";
    }
    @AfterEach
    void cleanDB() {
        taskService.deleteAllTasks();
    }
    @Test
    void test_createTask_0() {
        assertTrue(taskService.createTask(description1, TaskStatus.IN_PROGRESS));
    }
    @Test
    void test_createTask_1() {
        assertTrue(taskService.createTask(description2, TaskStatus.TODO));
    }
    @Test
    void test_createTask_2() {
        // cant create a record with null description
        assertThrows(InvalidTaskDescriptionException.class, () -> {
            taskService.createTask(null,TaskStatus.IN_PROGRESS);
        });
    }
    @Test
    void test_createTask_3() {
        assertThrows(InvalidStatusException.class, () -> {
            taskService.createTask(description1,null);
        });
    }
    @Test
    void test_deleteTask_0() throws SQLException {
        taskDAO.save(new Task("24680", "testing the delete method for taskService Class", TaskStatus.IN_PROGRESS));
        assertTrue(taskService.deleteTask("24680"));
    }
    @Test
    void test_deleteTask_1() throws SQLException {
        taskDAO.save(new Task("13579", "testing123", TaskStatus.DONE));
        assertTrue(taskService.deleteTask("13579"));
    }
    @Test
    void test_deleteTask_2() throws SQLException {
        assertThrows(InvalidTaskIdException.class, () -> {
            taskService.deleteTask(null);
        });
    }
    @Test
    void test_deleteTask_3() throws SQLException {
        assertFalse(taskService.deleteTask("00000"));
    }
    @Test
    void test_getById_0() throws SQLException {
        Task t1 = new Task("10101", "testing retrieval", TaskStatus.IN_PROGRESS);
        taskDAO.save(t1);
        if(taskService.getTaskByID("10101").isPresent()) {
            assertEquals(t1, taskService.getTaskByID("10101").get());
        }
    }
    @Test
    void test_getById_1() throws SQLException {
        assertThrows(InvalidTaskIdException.class, () -> {
           taskService.getTaskByID(null);
        });
    }
    @Test
    void test_getById_2() throws SQLException {

        assertEquals(Optional.empty(),taskService.getTaskByID("DNE45"));
    }
    @Test
    void test_getById_3() throws SQLException {
        assertThrows(InvalidTaskIdException.class, () -> {
            taskService.getTaskByID("NA");
        });
    }
    @Test
    void test_getByDescription_0() throws SQLException {
        Task t1 = new Task("10101", "testing retrieval", TaskStatus.IN_PROGRESS);
        taskDAO.save(t1);
        assertEquals(t1, taskService.getByDescription("testing retrieval").get());
    }
    @Test
    void test_getByDescription_1() throws SQLException {
        assertThrows(InvalidTaskDescriptionException.class, () -> {
           taskService.getByDescription(null);
        });
    }
    @Test
    void test_getByDescription_2() throws SQLException {
        assertEquals(Optional.empty(), taskService.getTaskByID("DNE67"));
    }
    @Test
    void test_getByDescription_3() throws SQLException {
        assertThrows(InvalidTaskDescriptionException.class, () -> {
            taskService.getByDescription("NA");
        });
    }

    @Test
    void test_updateDescription_0() throws SQLException {
        Task t1 = new Task("10101", "going to update this description", TaskStatus.TODO);
        taskDAO.save(t1);
        assertTrue(taskService.updateDescription("10101", "updated!"));
    }
    @Test
    void test_updateDescription_1() throws SQLException {
        Task t2 = new Task("1805M", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(t2);
        assertFalse(taskService.updateDescription("WRONG", "the id is wrong"));
    }
    @Test
    void test_updateDescription_2() throws SQLException {
        Task t2 = new Task("1805M", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(t2);
        assertThrows(InvalidTaskDescriptionException.class, () -> {
            taskService.updateDescription("1805M", null);
        });
    }
    @Test
    void test_updateDescription_3() throws SQLException {
        Task t2 = new Task("1805M", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(t2);
        assertThrows(InvalidTaskIdException.class, () -> {
            taskService.updateDescription(null, "update my linkedIn page summary");
        });
    }
    @Test
    void test_updateStatus_0() throws SQLException {
        Task t1 = new Task("10101", "going to update this description", TaskStatus.TODO);
        taskDAO.save(t1);
        assertTrue(taskService.updateStatus("10101", TaskStatus.IN_PROGRESS));
    }
    @Test
    void test_updateStatus_1() throws SQLException {
        Task t2 = new Task("1805M", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(t2);
        assertFalse(taskService.updateStatus("WRONG", TaskStatus.IN_PROGRESS));
    }
    @Test
    void test_updateStatus_2() throws SQLException {
        Task t2 = new Task("1805M", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(t2);
        assertThrows(InvalidStatusException.class, () -> {
            taskService.updateStatus("1805M", null);
        });
    }
    @Test
    void test_updateStatus_3() throws SQLException {
        Task t2 = new Task("1805M", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(t2);
        assertThrows(InvalidTaskIdException.class, () -> {
            taskService.updateStatus(null, TaskStatus.DONE);
        });
    }
    @Test
    void test_getAllByStatus_0() throws SQLException {
        Task task1 = new Task("00000", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        Task task2 = new Task("11111", "update my linkedIn page",TaskStatus.DONE);
        Task task3 = new Task("22222", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        Task task4 = new Task("33333", "update my linkedIn page",TaskStatus.TODO);
        Task task5 = new Task("44444", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(task1); taskDAO.save(task2); taskDAO.save(task3); taskDAO.save(task4); taskDAO.save(task5);
        List<Task> expectedList = new ArrayList<>();
        expectedList.add(task1);expectedList.add(task3);expectedList.add(task5);
        List<Task> resultList = taskService.getAllByStatus(TaskStatus.IN_PROGRESS);
        assertEquals(expectedList,resultList);
    }
    @Test
    void test_getAllByStatus_1() throws SQLException {
        Task task1 = new Task("00000", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        Task task2 = new Task("11111", "update my linkedIn page",TaskStatus.DONE);
        Task task3 = new Task("22222", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        Task task4 = new Task("33333", "update my linkedIn page",TaskStatus.TODO);
        Task task5 = new Task("44444", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(task1); taskDAO.save(task2); taskDAO.save(task3); taskDAO.save(task4); taskDAO.save(task5);
        List<Task> expectedList = new ArrayList<>();
        expectedList.add(task2);
        List<Task> resultList = taskService.getAllByStatus(TaskStatus.DONE);
        assertEquals(expectedList,resultList);
    }
    @Test
    void test_getAllByStatus_2() throws SQLException {
        Task task1 = new Task("00000", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        Task task2 = new Task("11111", "update my linkedIn page",TaskStatus.DONE);
        Task task3 = new Task("22222", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        Task task4 = new Task("33333", "update my linkedIn page",TaskStatus.DONE);
        Task task5 = new Task("44444", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(task1); taskDAO.save(task2); taskDAO.save(task3); taskDAO.save(task4); taskDAO.save(task5);
        List<Task> expectedList = new ArrayList<>();
        List<Task> resultList = taskService.getAllByStatus(TaskStatus.TODO);
        assertEquals(expectedList,resultList);
    }
    @Test
    void test_getAllByStatus_3() throws SQLException {
        Task task1 = new Task("00000", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        Task task2 = new Task("11111", "update my linkedIn page",TaskStatus.DONE);
        Task task3 = new Task("22222", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        Task task4 = new Task("33333", "update my linkedIn page",TaskStatus.DONE);
        Task task5 = new Task("44444", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(task1); taskDAO.save(task2); taskDAO.save(task3); taskDAO.save(task4); taskDAO.save(task5);
        assertThrows(InvalidStatusException.class, () -> {
            taskService.getAllByStatus(null);
        });
    }
    @Test
    void test_checkDuplicateId_0() throws SQLException {
        Task task1 = new Task("00000", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(task1); // save task to db
        assertTrue(taskService.checkDuplicateID("00000")); // should return true
    }
    @Test
    void test_checkDuplicateId_1() throws SQLException {
        Task task1 = new Task("00000", "update my linkedIn page",TaskStatus.IN_PROGRESS );
        taskDAO.save(task1); // save task to db
        assertFalse(taskService.checkDuplicateID("10000")); // should return true
    }
    @Test
    void test_checkDuplicateId_2() throws SQLException {
        assertThrows(InvalidTaskIdException.class, () -> {
            taskService.checkDuplicateID(null);
        });
    }




}