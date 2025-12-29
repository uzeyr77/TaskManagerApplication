package test;
import Task.*;
import Task.Database;
import Task.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskDAOTest {
    Database database;
    Connection con;
    TaskDAO taskDAO;
    Task task;
    Task task2;
    @BeforeEach
    void setUp() throws SQLException {
        database = new Database();
        //con = database.getConnection();
        taskDAO = new TaskDAO(database);
        task = new Task("12345", "testing taskdao class", TaskStatus.IN_PROGRESS);
        task2 = new Task("UZIMA","TESTING SAVE", TaskStatus.TODO);
        taskDAO.save(task);
        taskDAO.save(task2);
    }
    @AfterEach
    void close() throws SQLException {
        //database.closeConnection(con);
        taskDAO.delete(task.getId());
        taskDAO.delete(task2.getId());
    }
    @Test
    void test_save_0() throws SQLException {
        if(taskDAO.get(task.getId()).isPresent()) {
            assertEquals(taskDAO.get(task.getId()).get(), task);
        } else {
            assertNull(taskDAO.get(task.getId()));
        }
    }

    @Test
    void test_save_2() throws SQLException {
        //taskDAO.save(task);
        if(taskDAO.get(task2.getId()).isPresent()) {
            assertEquals(taskDAO.get(task2.getId()).get(), task2);
        } else {
            assertNull(task2);
        }
    }
    @Test
    void test_save_3() throws SQLException {
        if(taskDAO.get("UZIMA").isPresent()) {
            assertEquals(taskDAO.get("UZIMA").get(), task2);
        } else {
            assertNull(task2);
        }
    }
    @Test
    void test_delete_0() throws SQLException {
        assertTrue(taskDAO.delete(task.getId()));
    }
    @Test
    void test_delete_1() throws  SQLException {
        assertTrue(taskDAO.delete(task2.getId()));
    }
    @Test
    void test_delete_2() throws SQLException {
        if(taskDAO.get(task.getId()).isPresent()) {
            System.out.println("theres been a mistake");
        }
        assertTrue(true);
    }
    @Test
    void test_delete_3() throws SQLException {
        assertFalse(taskDAO.delete("DNE12")); // should return false as the record with id = DNE12 does not exist
    }


    @Test
    void test_checkById_0() throws SQLException {
        assertTrue(taskDAO.checkById("12345")); // returns true since record is found in database
    }
    @Test
    void test_checkById_1() throws SQLException {
        assertTrue(taskDAO.checkById("UZIMA"));
    }
    @Test
    void test_checkById_2() throws SQLException {
        // should return false since no record of id = "67890" exists
        assertFalse(taskDAO.checkById("67890"));
    }
    @Test
    void test_updateDescription_0() throws SQLException {
        assertTrue(taskDAO.update("12345", "testing if updating the description"));

    }

    @Test
    void test_updatedDescription_1() throws SQLException {
        taskDAO.update("12345", "testing to see if updating the description works");
        if(taskDAO.get("12345").isPresent()) {
            String newDesc = taskDAO.get("12345").get().getDescription();
            assertEquals("testing to see if updating the description works", newDesc);
        }
    }
    @Test
    void test_updatedDescription_2() throws SQLException {
        taskDAO.update("UZIMA", "testing the dao class");
        if(taskDAO.get("UZIMA").isPresent()) {
            assertEquals("testing the dao class", taskDAO.get("UZIMA").get().getDescription());
        }
    }
    @Test
    void test_updatedStatus_0() throws SQLException{
        assertTrue(taskDAO.update("12345", TaskStatus.TODO));
    }
    @Test
    void test_updatedStatus_1() throws SQLException {
        taskDAO.update("12345", TaskStatus.DONE);
        if(taskDAO.get("12345").isPresent()) {
            assertEquals(TaskStatus.DONE,taskDAO.get("12345").get().getStatus() );
        }
    }
    @Test
    void test_updatedStatus_2() throws SQLException {
        taskDAO.update("UZIMA", TaskStatus.DONE);
        if(taskDAO.get("UZIMA").isPresent()) {
            assertEquals(TaskStatus.DONE,taskDAO.get("UZIMA").get().getStatus());
        }
    }
    @Test
    void test_get_0() throws SQLException {
        if(taskDAO.get(task.getId()).isPresent())
            assertEquals(taskDAO.get(task.getId()).get(), task);
    }
    @Test
    void test_get_1() throws SQLException {
        if(taskDAO.get(task2.getId()).isPresent())
            assertEquals(taskDAO.get(task2.getId()).get(), task2);
    }
    @Test
    void test_get_2() throws SQLException {
        assertFalse(taskDAO.get("RANDO").isPresent());

    }
    @Test
    void test_getByDescription_0() throws SQLException {
        if(taskDAO.getByDescription("testing taskdao class").isPresent()) {
            assertEquals(task,taskDAO.getByDescription("testing taskdao class").get());
        }
    }
    @Test
    void test_getByDescription_1() throws SQLException {
        if(taskDAO.getByDescription(task2.getDescription()).isPresent()) {
            assertEquals(task2,taskDAO.getByDescription(task2.getDescription()).get());
        }
    }
    @Test
    void test_getByDescription_2() throws SQLException{
        assertFalse(taskDAO.getByDescription("random task that is not in the database").isPresent());
    }
    @Test
    void test_getByStatus_0 () throws SQLException {
        if(taskDAO.getAllByStatus(TaskStatus.IN_PROGRESS).isEmpty()) {
            fail("The database should have a record");
        } else {
            //System.out.println(taskDAO.getAllByStatus(TaskStatus.IN_PROGRESS));
            assertTrue(taskDAO.getAllByStatus(TaskStatus.IN_PROGRESS).contains(task));
        }
    }
    @Test
    void test_getByStatus_1 () throws SQLException {
        if(taskDAO.getAllByStatus(TaskStatus.TODO).isEmpty()) {
            fail("The database should have a record");
        } else {
            //System.out.println(taskDAO.getAllByStatus(TaskStatus.IN_PROGRESS));
            assertTrue(taskDAO.getAllByStatus(TaskStatus.TODO).contains(task2));
        }
    }
    @Test
    void test_getByStatus_2 () throws SQLException {
        assertTrue(taskDAO.getAllByStatus(TaskStatus.DONE).isEmpty());
    }
    @Test
    void test_findAll_0() throws SQLException {
        assertTrue(taskDAO.findAll().contains(task));
    }
    @Test
    void test_findAll_1() throws SQLException {
        assertTrue(taskDAO.findAll().contains(task2));
    }
    @Test
    void test_findAll_2() throws SQLException {
        assertFalse(taskDAO.findAll().isEmpty());
    }
    @Test
    void test_findAll_3() throws SQLException {
        assertFalse(taskDAO.findAll().contains(new Task("RAND0", "task should not be found in database", TaskStatus.IN_PROGRESS)));
    }
}