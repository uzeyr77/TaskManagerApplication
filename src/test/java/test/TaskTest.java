package test;

import Task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import Task.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Task task0;
    Task task1;
    Task task2;
    Task task3;
    Task task4;
    @BeforeEach
    void resetTaskObjects() {
        task0 = new Task("12345","make junit tests for Task class", TaskStatus.IN_PROGRESS);
        task1 = new Task("ABCDE","go to gym", TaskStatus.TODO);
        task2 = new Task("UZEYR","go for a walk", TaskStatus.TODO);
        task3 = new Task("VALID","do the dishes", TaskStatus.IN_PROGRESS);
        task4 = new Task("YORKU","clean up my room", TaskStatus.DONE);
    }
    @Test
    void testCreationOfTask_1() {
        assertNotNull(task0);
    }
    @Test
    void testCreationOfTask_2() {
        assertNotNull(task1);
    }
    @Test
    void testGetID_0() {
        assertEquals("12345", task0.getId());
    }
    @Test
    void testGetID_1() {
        assertEquals("ABCDE",task1.getId());
    }
    @Test
    void testGetID_2() {
        assertEquals("VALID", task3.getId());
    }
    @Test
    void testGetDescription_1() {
        assertEquals("make junit tests for Task class", task0.getDescription());
    }
    @Test
    void testGetDescription_2() {
        assertEquals("go to gym", task1.getDescription());
    }
    @Test
    void testGetStatus_1() {
        assertEquals(TaskStatus.IN_PROGRESS,task0.getStatus());
    }
    @Test
    void testGetStatus_2() {
        assertEquals(TaskStatus.IN_PROGRESS,task3.getStatus());
    }
    @Test
    void testForDateCreation_1() {
        assertNotNull(task3.getDateCreated());
    }
    @Test
    void testForDateCreation_2() {
        assertNotNull(task4.getDateCreated());
    }
    @Test
    void testForValidCreatedDate() {
        LocalDate date = LocalDate.now();
        assertEquals(date.toString(), task4.getDateCreated().toString());
    }
    @Test
    void testForUpdateDateWithNoUpdate() {
        assertNull(task3.getDateUpdated());
    }

    @Test
    void testEquals_0() {
        Task copy = new Task("UZEYR","go for a walk", TaskStatus.TODO);
        assertTrue(task2.equals(copy));

    }
    @Test
    void testEquals_1_different_status() {
        Task copy = new Task("VALID","do the dishes", TaskStatus.DONE);
        assertFalse(task3.equals(copy));
    }
    @Test
    void testEquals_2_different_description() {
        Task copy = new Task("YORKU","clean up living room", TaskStatus.DONE);
        assertFalse(task4.equals(copy));
    }

    @Test
    void testEquals_1_should_return_false_when_status_is_different() {
        Task original = new Task("54321","workout", TaskStatus.TODO);
        Task copy = new Task("54321","workout", TaskStatus.DONE);
        assertNotEquals(original, copy);
    }
    @Test
    void testEquals_2_should_return_false_when_description_is_different() {
        Task original = new Task("ID123","workout", TaskStatus.TODO);
        Task copy = new Task("ID123","workout tmr", TaskStatus.TODO);
        assertNotEquals(original, copy);
    }


    @Test
    void testEquals_3_should_return_true_after_assignment() {
        Task original = new Task("67890","workout", TaskStatus.TODO);
        Task other = original;
        assertEquals(original, other);
    }

    @Test
    void testSettingStatus_should_return_true() {
        task0.setStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task0.getStatus());
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
        TaskStatus statusBefore = task3.getStatus();
        task3.setStatus(TaskStatus.DONE);
        assertNotEquals(statusBefore, task3.getStatus());
    }
    @Test
    void testDateUpdated_should_return_true() {
        Task t = new Task("00000", "finish building frontend", TaskStatus.IN_PROGRESS);
        assertNull(t.getDateUpdated());
    }
    @Test
    void testDateUpdated_should_return_false() {
        Task t = new Task("00000", "finish building frontend", TaskStatus.IN_PROGRESS);
        t.setStatus(TaskStatus.DONE);
        assertNotNull(t.getDateUpdated());
    }




}