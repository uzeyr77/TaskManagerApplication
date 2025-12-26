package Task;

import java.util.Random;

public class TaskIDGenerator {
    private static final String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random random = new Random();
    private static StringBuilder result = new StringBuilder();

    private TaskIDGenerator() {

    }

    public static String generateID() {
        result.delete(0, result.length());
        for(int i = 0; i < 5;i++) {
            result.append(alphabet.charAt(random.nextInt(0,36)));
        }

        return result.toString();
    }

}
