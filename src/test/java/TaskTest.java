import Task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Task task0;
    Task task1;
    Task task2;
    Task task3;
    Task task4;
    @BeforeEach
    void resetTaskCounter() {
        Task.count = 0;
    }
    @BeforeEach
    void resetTaskObjects() {
        task0 = new Task("make junit tests", "in-progress");
        task1 = new Task("go to gym", "to-do");
        task2 = new Task("go for a walk", "to-do");
        task3 = new Task("do the dishes", "in-progress");
        task4 = new Task("clean up my room", "done");
    }

    @Test
    void testGetDescription() {
        assertEquals("make junit tests", task0.getDescription());
    }

    @Test
    void testGetStatus() {
        assertEquals("in-progress",task0.getStatus());
    }

    @Test
    void testCreationOfTask() {
        assertNotNull(task0);
    }

    @Test
    void testForValidDescription () {
        assertNotNull(task0.getDescription());
    }
    @Test
    void testForValidStatus () {
        assertNotNull(task0.getStatus());
    }
    @Test
    void testGetID_0() {
        assertEquals(1, task0.getId());
    }
    @Test
    void testGetID_1() {
        assertEquals(3,task2.getId());
    }
    @Test
    void testGetID_2() {
        assertEquals(4, task3.getId());
    }

    @Test
    void testForDateCreation() {
        assertNotNull(task0.getCreatedAt());
    }
    @Test
    void testForValidCreatedDate() {
        LocalDate date = LocalDate.now();
        assertEquals(date.toString(), task0.getCreatedAt());
    }
    @Test
    void testForUpdateDateWithNoUpdate() {
        assertNull(task0.getUpdatedAt());
    }

    @Test
    void testEquals_0() {
        Task copy = new Task("make junit tests", "in-progress");
        assertTrue(task0.equals(copy));

    }

    @Test
    void testEquals_1_should_return_false_when_status_is_different() {
        Task original = new Task("workout", "to-do");
        Task copy = new Task("workout", "done");
        assertFalse(original.equals(copy));
    }
    @Test
    void testEquals_2_should_return_false_when_description_is_different() {
        Task original = new Task("workout", "to-do");
        Task copy = new Task("workout tmr", "to-do");
        assertFalse(original.equals(copy));
    }


    @Test
    void testEquals_3_should_return_true_after_assignment() {
        Task original = new Task("workout", "to-do");
        Task other = original;
        assertTrue(original.equals(other));
    }

    @Test
    void testSettingStatus_should_return_true() {
        task0.setStatus("done");
        assertEquals("done", task0.getStatus());
    }
    @Test
    void testSettingDescription_should_return_true() {
        task1.setDescription("go for a run");
        assertEquals("go for a run", task1.getDescription());
    }
    @Test
    void testSettingDescription_should_return_false_after_updating() {
        String descrpBefore = task2.getDescription();
        task2.setDescription("go for a run");
        assertNotEquals(descrpBefore, task2.getDescription());
    }
    @Test
    void testSettingStatus_should_return_false_after_updating() {
        String statusBefore = task3.getStatus();
        task3.setStatus("done");
        assertNotEquals(statusBefore, task3.getStatus());
    }




}