import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

//
//    void go_testingAddingTask() {
//        // creating "fake input phase"
//        // fake predetermined input instead of from the reader
//        TaskManager tm = new TaskManager();
//
//
//        String fakeInput = "add go to the store\nto-do\n-1";
//
//        // Scanner takes in the fakeInput string as array of bytes instead of the usual System.in
//        Scanner fakeScanner = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
//
//        // capturing phase
//
//        // inplace instead of having sout statements, basically writes to a space in memory instead of having sout write to command line
//        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
//
//        // this basically writes into the location output buffer, like sout prints to the cmdline this prints to the location outputBuffer
//        PrintStream fakeOut = new PrintStream(outputBuffer);
//
//        UserInterface ui = new UserInterface();
//
//        ui.go(tm, fakeScanner, fakeOut);
//
//
//        String output = outputBuffer.toString();
//
//        System.out.println(output);
//
//        assertTrue(output.contains("Task added Successfully (junit)"));
//
//
//    }
//
//    @Test
//    void go_testingDeletingTask() {
//    TaskManager tm = new TaskManager();
//    ObjectMapper om = new ObjectMapper();
//    String fakeInput = "add go to the store and buy sugar\nto-do\nadd wash clothing\nto-do\ndelete 2\n-1";
//    Scanner fakeScanner = new Scanner(new ByteArrayInputStream(fakeInput.getBytes())); // fake scanner that takes in our fake string
//    ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream(); // space in memory for output
//    PrintStream fakeOut = new PrintStream(outputBuffer); // sets up location in memory
//
//    UserInterface ui = new UserInterface();
//    ui.go(tm, fakeScanner, fakeOut);
//    String output = outputBuffer.toString(); // get the output from the location in memory
//    System.out.println(output);
//
//    assertTrue(output.contains("Task has been deleted successfully"));
//
//    }
//
//    @Test
//    void go_testingUpdatingTask() {
//    TaskManager tm = new TaskManager();
//    ObjectMapper om = new ObjectMapper();
//    String fakeInput = "add go to the store and get milk\nto-do\nupdate 1 go to the store and dont get milk\n-1";
//    ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
//    Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
//    PrintStream fakeOut = new PrintStream(outputBuffer);
//    UserInterface ui = new UserInterface();
//
//    ui.go(tm, s, fakeOut);
//
//    String output = outputBuffer.toString();
//
//        //System.out.println(output);
//    assertTrue(output.contains("Task 1 successfully updated"));
//

    @Test
    void run_testingListingAllTasks() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "list\n-1\n";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);

        String output = outputBuffer.toString();
        System.out.println(output);
        
        //assertTrue(output.contains("Task{id=1, description='to coffee shop', status='to-do', created at= '2025-08-14', updated at= 'N/A'} Task{id=2, description='for a run', status='to-do', created at= '2025-08-14', updated at= 'N/A'} Task{id=3, description='groceries', status='in-progress', created at= '2025-08-14', updated at= 'N/A'}"));
    }

//    @Test
//    void go_testingListingInProgress() {
//        TaskManager tm = new TaskManager();
//        ObjectMapper om = new ObjectMapper();
//        String fakeInput = "list";
//        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
//        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
//        PrintStream fakeOut = new PrintStream(outputBuffer);
//        UserInterface ui = new UserInterface();
//
//        ui.go(tm, s, fakeOut);
//
//        String output = outputBuffer.toString();
//
//        assertTrue(output.contains("Task{id=1, description='go for a run', status='in-progress', created at= '2025-08-15', updated at= 'N/A'} Task{id=2, description='go to the shops and buy a khamis', status='in-progress', created at= '2025-08-15', updated at= 'N/A'} Task{id=3, description='find a wife', status='in-progress', created at= '2025-08-15', updated at= 'N/A'}"));
//    }
//
//
//    @Test
//    void go_testingListingTodo() {
//        TaskManager tm = new TaskManager();
//        ObjectMapper om = new ObjectMapper();
//        String fakeInput = "add go for a run\nto-do\nadd go to the shops and buy a khamis\nto-do\nadd find a wife\nto-do\nlist to-do\n-1\n";
//        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
//        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
//        PrintStream fakeOut = new PrintStream(outputBuffer);
//        UserInterface ui = new UserInterface();
//
//        ui.go(tm, s, fakeOut);
//
//        String output = outputBuffer.toString();
//
//        assertTrue(output.contains("Task{id=1, description='go for a run', status='to-do', created at= '2025-08-15', updated at= 'N/A'} Task{id=2, description='go to the shops and buy a khamis', status='to-do', created at= '2025-08-15', updated at= 'N/A'} Task{id=3, description='find a wife', status='to-do', created at= '2025-08-15', updated at= 'N/A'}"));
//
//    }
//
//    @Test
//    void go_testingListingDone() {
//        TaskManager tm = new TaskManager();
//        ObjectMapper om = new ObjectMapper();
//        String fakeInput = "add go for a run\ndone\nadd go to the shops and buy a khamis\ndone\nadd find a wife\ndone\nlist done\n-1\n";
//        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
//        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
//        PrintStream fakeOut = new PrintStream(outputBuffer);
//        UserInterface ui = new UserInterface();
//
//        ui.go(tm, s, fakeOut);
//
//        String output = outputBuffer.toString();
//        System.out.println(output);
//
//        assertTrue(output.contains("Task{id=2, description='go to the shops and buy a khamis', status='done', created at= '2025-08-15', updated at= 'N/A'} Task{id=3, description='find a wife', status='done', created at= '2025-08-15', updated at= 'N/A'}"));
//
//    }
//
//    @Test
//
//    void go_testingMarkInProgress() {
//        TaskManager tm = new TaskManager();
//        ObjectMapper om = new ObjectMapper();
//        String fakeInput = "add go to the store\nin-progress\nmark-done 1\n-1";
//        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
//        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
//        PrintStream fakeOut = new PrintStream(outputBuffer);
//        UserInterface ui = new UserInterface();
//
//        ui.go(tm, s, fakeOut);
//
//        String output = outputBuffer.toString();
//        System.out.println(output);
//
//    }
//
//    // UPDATED TESTS
//    @Test
//    void run_testAddingTask() {
//        TaskManager tm = new TaskManager();
//        ObjectMapper om = new ObjectMapper();
//        String fakeInput = "add go to the store and get icecream\nin-progress\n-1";
//        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
//        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
//        PrintStream fakeOut = new PrintStream(outputBuffer);
//        UserInterface ui = new UserInterface();
//
//        ui.run(tm, s, fakeOut);
//
//        String output = outputBuffer.toString();
//
//        System.out.println(output);
//
//    }
//
    @Test
    void run_testDeletingTask() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store and get icecream\nin-progress\nadd go to juma\nto-do\ndelete 2\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);

        String output = outputBuffer.toString();

        System.out.println(output);

        assertTrue(output.contains("Task 2 has been deleted"));
    }

    @Test

    void run_testUpdatingTaskDescription() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store and get icecream\nin-progress\nupdate 1\ngo to the store and get icecream and peanut butter\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        System.out.println(output);

        assertTrue(output.contains("Task 1 has been updated"));
    }

    @Test
    void run_testListAll() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store\nin-progress\nlist\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        //System.out.println(output);

        assertTrue(output.contains("Task{id=1, description='go to the store', status='in-progress', created at= '2025-08-16', updated at= 'N/A'}"));
    }

    @Test
    void run_testListingInProg() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store\nin-progress\nadd go for a walk\nto-do\nadd buy milk\ndone\nlist in-progress\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        //System.out.println(output);

        assertTrue(output.contains("Task{id=1, description='go to the store', status='in-progress', created at= '2025-08-16', updated at= 'N/A'}"));
    }
    @Test
    void run_testingListingTodo() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store\nin-progress\nadd go for a walk\nto-do\nadd buy milk\ndone\nlist to-do\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        //System.out.println(output);

        assertTrue(output.contains("Task{id=2, description='go for a walk', status='to-do', created at= '2025-08-16', updated at= 'N/A'}"));

    }

    @Test
    void run_testingListingDone() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store\nin-progress\nadd go for a walk\nto-do\nadd buy milk\ndone\nlist done\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        //System.out.println(output);

        assertTrue(output.contains("Task{id=3, description='buy milk', status='done', created at= '2025-08-16', updated at= 'N/A'}"));
    }

    @Test
    void run_testingMarkInProgress() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store\nto-do\nadd go for a walk\nto-do\nmark-in-progress 1\nadd read Quran\nin-progress\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        System.out.println(output);

        assertTrue(output.contains("Task 1 has been marked in progress"));

    }
    @Test
    void run_testingMarkTodo() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store\nto-do\nadd go for a walk\nto-do\nadd read Quran\nin-progress\nmark-to-do 3\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        System.out.println(output);

        assertTrue(output.contains("Task 3 has been marked as to-do"));
    }

    @Test
    void run_testingMarkingDone() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "add go to the store\nto-do\nadd go for a walk\nto-do\nadd read Quran\nin-progress\nmark-done 3\n-1";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        System.out.println(output);

        assertTrue(output.contains("Task 3 has been marked as done"));
    }
    @Test

    void run_testingListingAllWhenListIsEmpty() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "list\n-1\n";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        //System.out.println(output);

        assertTrue(output.contains("LIST IS EMPTY!\nAdd tasks to see list."));
    }
    @Test

    void run_testingListingInProgWhenListIsEmpty() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "list in-progress\n-1\n";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        //System.out.println(output);

        assertTrue(output.contains("LIST IS EMPTY!\nAdd tasks to see list."));
    }
    @Test

    void run_testingListingToDoWhenListIsEmpty() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "list to-do\n-1\n";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        //System.out.println(output);

        assertTrue(output.contains("LIST IS EMPTY!\nAdd tasks to see list."));
    }

    @Test

    void run_testingListingDoneWhenListIsEmpty() {
        TaskManager tm = new TaskManager();
        ObjectMapper om = new ObjectMapper();
        String fakeInput = "list done\n-1\n";
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        Scanner s = new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));
        PrintStream fakeOut = new PrintStream(outputBuffer);
        UserInterface ui = new UserInterface();

        ui.run(tm, s, fakeOut);
        String output = outputBuffer.toString();
        //System.out.println(output);

        assertTrue(output.contains("LIST IS EMPTY!\nAdd tasks to see list."));
    }





}