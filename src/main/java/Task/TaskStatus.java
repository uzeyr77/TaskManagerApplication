package Task;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE,
    TBD,
    NONE;


    private TaskStatus() {

    }

//    @Override
//    public String toString() {
//        return this.toString().toLowerCase();
//    }
    public static void main (String [] args) {
        System.out.println(TaskStatus.TODO);
    }

}
