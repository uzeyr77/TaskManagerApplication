package SQLiteDatabase;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {

    public static void connect() {
        var url = "jdbc:sqlite:C:/Users/uzeyr/OneDrive/TasksDatabase";


        try(var conn = DriverManager.getConnection(url)) {
            System.out.println("connection was established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void main (String [] args) {
        connect();

    }
}
