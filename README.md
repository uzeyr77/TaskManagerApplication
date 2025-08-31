A simple project in java that tracks and manages your tasks completely from the command line.
# Technologies Used
* Intellij Idea
* Maven
* Junit 5
* Java 24
# Features
* Add, update and delete tasks.
* Mark tasks based on their status(done, in-progress).
* List all tasks and tasks based on their status.
* User Interface to except inputs from the command line.

# Usage
This is a very simple task tracking application. The program runs completely from the command line and it allows you to interact with the user interface by adding, updating, and deleting tasks as well as listing. It should be noted that all tasks must have the status of **to-do, in-progress, or done**. When ever you want to exit just enter -1. 

**It should be noted that the tasks have IDs from 1 to how ever many tasks you have. So the first task added has ID 1, the second has ID 2 and so on.**  
<img width="469" height="227" alt="image" src="https://github.com/user-attachments/assets/eabb745f-bb90-48be-863a-bf54f14aed9d" />
<img width="1647" height="469" alt="image" src="https://github.com/user-attachments/assets/1888e808-0e03-41c5-8f72-946b0e3ce83e" />  
# Input format  
 Format for **adding**: **[add] + [description of task] [\n] [status]**  

Format for **deleting**: **[delete] + [id of task] + [\n]**  
  
  <img width="1161" height="357" alt="image" src="https://github.com/user-attachments/assets/5d58e6fb-400f-4310-b3c2-c5637b8352f1" />

The format for **Listing** tasks:    

**To list all:** **[list] + [\n]**

**To list based on status (to-do, in-progress, done):** **[list] + [status] + [\n]**  

Format for **Marking** tasks: **[mark-status] + [id of task]**  

<img width="1481" height="336" alt="image" src="https://github.com/user-attachments/assets/fbd32aaa-d70a-4a6f-8694-3136686c7c11" />  



**Purpose**

  The main reason I decided to tackle this project was to practice OOP concepts, working with json files, and making an interactive user interface in the command line.
