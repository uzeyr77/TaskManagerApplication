import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.Scanner;

public class UserInterface {
    //    Scanner s;
    //String input;
    TaskManager tm;
    String sentence;

    public UserInterface() {
        //this.s = new Scanner(System.in);
        //this.input = "";
        this.tm = new TaskManager();
        this.sentence = "";
    }

//    public void go(TaskManager tm, Scanner s, PrintStream out) {
//        // prompt the user for what they want
//        sentence = "";
//        while (true) {
//            sentence = s.nextLine();
//            if (sentence.equals("-1")) break;
//            out.println("Welcome to task manager!");
//            String[] words = sentence.split(" ");
//            if (words.length == 0) {
//                out.println("too short");
//            }
//            int identification = 0;
//            String cmd = words[0];
//            if (words.length == 1) {
//                if (cmd.equals("list"))
//                    out.println("list:");
//                out.println(tm.listAll());
//            } else {
//
//                try {
//                    identification = Integer.parseInt(words[1]);
//
//                    StringBuilder sb = new StringBuilder();
//
//                    for (int i = 0; i < words.length; i++) {
//                        sb.append(words[i]);
//                        if (i < words.length - 1) sb.append(" ");
//                        //System.out.println(sb + " here is the description");
//                    }
//                    if (cmd.equals("update")) {
//                        // call tm.find()
//                        // if it exists it will return the index 0 - list.length-1
//                        // if it does not exist returns -1
//
//                        // if it does exist call tm.updateTask(taskID)
//
//                        try {
//                            identification = Integer.parseInt(words[1]);
//
//                            if (tm.findTask(identification) != -1) {
//                                tm.update(identification, sb.toString());
//                                out.println("Task " + identification + " successfully updated");
//                            } else {
//                                out.println("The task with ID, " + identification + " does not exist");
//                            }
//                        } catch (NumberFormatException e) {
//                            throw new RuntimeException(e.getMessage());
//                        }
//
//
//                        if (tm.findTask(identification) == -1) {
//                            out.println("Task DNE");
//                        } else {
//                            tm.update(identification, sb.toString());
//
//                        }
//
//                    } else if (cmd.equals("delete")) {
//
//                        if (tm.findTask((identification)) == -1) {
//                            out.println("Task DNE");
//                        } else {
//                            tm.delete(identification);
//                            out.println("Task has been deleted Successfully");
//                        }
//
//                    }
//
//
//                } catch (NumberFormatException e) {
//                    out.println(e.getMessage());
//                    StringBuilder sb = new StringBuilder();
//
//                    for (int i = 1; i < words.length; i++) {
//                        sb.append(words[i]);
//                        if (i < words.length - 1) sb.append(" ");
//                    }
//                    if (cmd.equals("add")) {
//                        out.println("What is the status of the task?");
//                        String status = s.nextLine();
//                        Task t = new Task(sb.toString(), status);
//                        tm.addTaskToJson(t);
//                        out.print("Task added Successfully (junit)");
//                    } else if (cmd.equals("list")) {
//                        if (words[1].equals("done")) {
//                            out.println("list of done:");
//                            out.println(tm.listDone());
//                            // call a tm method to do so
//                        } else if (words[1].equals("to-do")) {
//                            out.println("list of to-do");
//                            out.println(tm.listTodo());
//                        } else if (words[1].equals("in-progress"))
//                            out.println("list of in-progress");
//                        out.println(tm.listInProg());
//                    } else if (cmd.equals("mark-in-progress")) {
//                        // call a method to update a status
//                        if (tm.findTask(identification) == -1) {
//                            out.println("Task to mark as in-progress not found");
//                        } else {
//                            tm.updateStatus(identification, "in-progress");
//                            out.println("updated the task status");
//                        }
//
//
//                    } else if (cmd.equals("mark-done")) {
//                        if (tm.findTask(identification) == -1) {
//                            out.println("Task to mark as in-progress not found");
//                        } else {
//                            tm.updateStatus(identification, "done");
//                            out.println("updated the task status");
//                        }
//                    } else {
//                        out.println("invalid input");
//                    }
//
//                }
//
//            }
//        }
//    }
//
//    public void go(TaskManager tm) {
//        // prompt the user for what they want
//        Scanner s = new Scanner(System.in);
//        String sentence = "";
//        while (!sentence.equals("-1")) {
//            System.out.println("Welcome to task manager!");
//            sentence = s.nextLine();
//            String[] words = sentence.split(" ");
//            if (words.length == 0) {
//                System.out.println("too short");
//            }
//            int identification = 0;
//            String cmd = words[0];
//            if (words.length == 1) {
//                if (cmd.equals("list"))
//                    System.out.println("list:");
//                System.out.println(tm.listAll());
//            } else {
//
//                try {
//                    identification = Integer.parseInt(words[1]);
//
//                    StringBuilder sb = new StringBuilder();
//
//                    for (int i = 1; i < words.length; i++) {
//                        sb.append(words[i]);
//                        if (i < words.length - 1) sb.append(" ");
//
//                    }
//                    if (cmd.equals("update")) {
//                        // call tm.find()
//                        // if it exists it will return the index 0 - list.length-1
//                        // if it does not exist returns -1
//
//                        // if it does exist call tm.updateTask(taskID)
//                        try {
//                            identification = Integer.parseInt(words[1]);
//
//                            if (tm.findTask(identification) != -1) {
//                                tm.update(identification, sb.toString());
//                                System.out.println("Task id " + identification + " successfully updated");
//                            } else {
//                                System.out.println("The task with ID, " + identification + " does not exist");
//                            }
//                        } catch (NumberFormatException e) {
//                            throw new RuntimeException(e.getMessage());
//                        }
////
////
////                            if (tm.findTask(identification) == -1) {
////                                System.out.println("Task DNE");
////                            } else {
////                                tm.update(identification, sb.toString());
////
////                            }
//
//                    } else if (cmd.equals("delete")) {
//
//                        if (tm.findTask((identification)) == -1) {
//                            System.out.println("Task DNE");
//                        } else {
//                            tm.delete(identification);
//                            System.out.println("Task has been deleted Successfully");
//                        }
//
//
//                    } else if (cmd.equals("mark-in-progress")) {
//                        // call a method to update a status
//                        if (tm.findTask(identification) == -1) {
//                            System.out.println("Task to mark as in-progress not found");
//                        } else {
//                            tm.updateStatus(identification, "in-progress");
//                            System.out.println("updated the task status");
//                        }
//
//
//                    } else if (cmd.equals("mark-done")) {
//                        if (tm.findTask(identification) == -1) {
//                            System.out.println("Task to mark as in-progress not found");
//                        } else {
//                            tm.updateStatus(identification, "done");
//                            System.out.println("updated the task status");
//                        }
//
//                    } else if (cmd.equals("list")) {
//                        if (words[1].equals("done")) {
//                            System.out.println("list of done:");
//                            tm.listDone();
//                            // call a tm method to do so
//                        } else if (words[1].equals("to-do")) {
//                            System.out.println("list of to-do");
//                            tm.listTodo();
//                        } else if (words[1].equals("in-progress"))
//                            System.out.println("list of in-progress");
//                        tm.listInProg();
//                    } else {
//                        System.out.println("improper request error");
//                    }
//
//
//                } catch (NumberFormatException e) {
//                    System.out.println(e.getMessage());
//                    StringBuilder sb = new StringBuilder();
//
//                    for (int i = 2; i < words.length; i++) {
//                        sb.append(words[i]);
//                        if (i < words.length - 1) sb.append(" ");
//                    }
//                    if (cmd.equals("add")) {
//                        System.out.println("What is the status of the task?");
//                        String status = s.nextLine();
//                        Task t = new Task(sb.toString(), status);
//                        tm.addTaskToJson(t);
//                    }
//
//
//                }
//
//            }
//        }
//    }

    public void run(TaskManager tm) {
        Scanner s = new Scanner(System.in);
        System.out.println("WELCOME TO TASK MANAGER");
        String sentence = "";
        while (true) {
            sentence = s.nextLine();
            if (sentence.equals("-1")) break;

            if (sentence.isEmpty()) {
                System.out.println("Invalid input");
                break;
            } else if (sentence.equals("list")) {
                if(tm.getTasks().isEmpty()) {
                    System.out.println("LIST IS EMPTY!\nAdd tasks to see list.");
                    continue;
                }
                System.out.println(tm.listAll());
                continue;
            } else if(sentence.equals("list in-progress")) {
                if(tm.getTasks().isEmpty()) {
                    System.out.println("LIST IS EMPTY!\nAdd tasks to see list.");
                    continue;
                }
                System.out.println(tm.listInProg());
                continue;
            } else if (sentence.equals("list to-do")) {
                if(tm.getTasks().isEmpty()) {
                    System.out.println("LIST IS EMPTY!\nAdd tasks to see list.");
                    continue;
                }
                System.out.println(tm.listTodo());
                continue;
            } else if (sentence.equals("list done")) {
                if(tm.getTasks().isEmpty()) {
                    System.out.println("LIST IS EMPTY!\nAdd tasks to see list.");
                    continue;
                }
                System.out.println(tm.listDone());
                continue;
            }

            String[] words = sentence.split(" ");
            String cmd = words[0];

            if (words.length == 2) {
                // then its either mark, delete, or update a specific type of tasks, in that case parse words[1] as integer
                try {
                    int taskId = Integer.parseInt(words[1]);
                    if (cmd.equals("delete")) {
                        if(tm.findTask(taskId) == -1) System.out.println("TASK DNE (CANT DELETE)");

                        // else
                        tm.delete(taskId);
                        System.out.println("Task " + taskId + " has been deleted");
                    } else if (cmd.equals("update")) {
                        if(tm.findTask(taskId) == -1) System.out.println("TASK DNE (CANT UPDATE)");

                        System.out.println("Provide the updates");
                        String descriptionUpdate = s.nextLine();
                        // else
                        tm.update(taskId, descriptionUpdate);
                        System.out.println("Task " + taskId + " has been updated");

                    } else if (cmd.equals("mark-in-progress")) {
                        if(tm.findTask(taskId) == -1) System.out.println("TASK DNE (CANT UPDATE STATUS)");

                        // else
                        tm.updateStatus(taskId, "in-progress");
                        System.out.println("Task " + taskId + " has been marked in progress");
                    } else if (cmd.equals("mark-to-do")) {
                        if(tm.findTask(taskId) == -1) System.out.println("TASK DNE (CANT UPDATE STATUS)");

                        // else
                        tm.updateStatus(taskId, "to-do");
                        System.out.println("Task " + taskId + " has been marked as to-do");
                    } else if (cmd.equals("mark-done")) {
                        if(tm.findTask(taskId) == -1) System.out.println("TASK DNE (CANT UPDATE STATUS)");

                        // else
                        tm.updateStatus(taskId, "done");
                        System.out.println("Task " + taskId +" has been marked as done");
                    }


                } catch (NumberFormatException e) {
                    throw new RuntimeException(e.getMessage());
                }
                // here we will handle the listing of specific tasks becaause there is no need to parse an integer

            } else { // words > length(2) then we know we are adding a task
                // then it is to add a new task, no parsing required
                if (cmd.equals("add")) {
                    StringBuilder description = new StringBuilder();
                    for (int i = 1; i < words.length; i++) {
                        description.append(words[i]);
                        if (i < words.length - 1) description.append(" ");
                    }
                    System.out.println("What is the status of the task?");
                    String status = s.nextLine();
                    Task t = new Task(description.toString(), status);
                    tm.addTaskToJson(t);

                }

            }


        }


    }




    public void run(TaskManager tm, Scanner s, PrintStream out) {
        out.println("WELCOME TO TASK MANAGER");
        String sentence = "";
        while (true) {
            sentence = s.nextLine();
            if (sentence.equals("-1")) break;

            if (sentence.isEmpty()) {
                out.print("Invalid input");
                break;
            } else if (sentence.equals("list")) {
                if(tm.getTasks().isEmpty()) {
                    out.println("LIST IS EMPTY!\nAdd tasks to see list.");
                    continue;
                }
                out.print(tm.listAll());
                continue;
            } else if(sentence.equals("list in-progress")) {
                if(tm.getTasks().isEmpty()) {
                    out.println("LIST IS EMPTY!\nAdd tasks to see list.");
                    continue;
                }
                out.println(tm.listInProg());
                continue;
            } else if (sentence.equals("list to-do")) {
                if(tm.getTasks().isEmpty()) {
                    out.println("LIST IS EMPTY!\nAdd tasks to see list.");
                    continue;
                }
                out.println(tm.listTodo());
                continue;
            } else if (sentence.equals("list done")) {
                if(tm.getTasks().isEmpty()) {
                    out.println("LIST IS EMPTY!\nAdd tasks to see list.");
                    continue;
                }
                out.println(tm.listDone());
                continue;
            }

            String[] words = sentence.split(" ");
            String cmd = words[0];

            if (words.length == 2) {
                // then its either mark, delete, or update a specific type of tasks, in that case parse words[1] as integer
                try {
                    int taskId = Integer.parseInt(words[1]);
                    if (cmd.equals("delete")) {
                        if(tm.findTask(taskId) == -1) out.println("TASK DNE (CANT DELETE)");

                        // else
                        tm.delete(taskId);
                        out.print("Task " + taskId + " has been deleted");
                    } else if (cmd.equals("update")) {
                        if(tm.findTask(taskId) == -1) out.println("TASK DNE (CANT UPDATE)");

                        out.println("Provide the updates");
                        String descriptionUpdate = s.nextLine();
                        // else
                        tm.update(taskId, descriptionUpdate);
                        out.print("Task " + taskId + " has been updated");

                    } else if (cmd.equals("mark-in-progress")) {
                        if(tm.findTask(taskId) == -1) out.println("TASK DNE (CANT UPDATE STATUS)");

                        // else
                        tm.updateStatus(taskId, "in-progress");
                        out.print("Task " + taskId + " has been marked in progress");
                    } else if (cmd.equals("mark-to-do")) {
                        if(tm.findTask(taskId) == -1) out.println("TASK DNE (CANT UPDATE STATUS)");

                        // else
                        tm.updateStatus(taskId, "to-do");
                        out.println("Task " + taskId + " has been marked as to-do");
                    } else if (cmd.equals("mark-done")) {
                        if(tm.findTask(taskId) == -1) out.println("TASK DNE (CANT UPDATE STATUS)");

                        // else
                        tm.updateStatus(taskId, "done");
                        out.println("Task " + taskId +" has been marked as done");
                    }


                } catch (NumberFormatException e) {
                    throw new RuntimeException(e.getMessage());
                }
                // here we will handle the listing of specific tasks becaause there is no need to parse an integer

                } else { // words > length(2) then we know we are adding a task
                    // then it is to add a new task, no parsing required
                    if (cmd.equals("add")) {
                        StringBuilder description = new StringBuilder();
                        for (int i = 1; i < words.length; i++) {
                            description.append(words[i]);
                            if (i < words.length - 1) description.append(" ");
                        }
                        System.out.println("What is the status of the task?");
                        String status = s.nextLine();
                        Task t = new Task(description.toString(), status);
                        tm.addTaskToJson(t);
                        
                    }

                }


            }


        }


    }
//
//                // logic flow:
//                // two types of requests that will alter the json file:
//                // ADDING a new task OR updating/deleting an existing task
//                // To differ is to see if an ID is given with the input from the user
//                // to differ we can have a try-catch block where we try to parse the second word as an
//                // integer; if it parses then we check if the word following it is delete or update
//                // if it throws an exception we know a task is being added
//
//
//            }
//        }







