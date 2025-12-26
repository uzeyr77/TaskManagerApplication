package Task;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
    private static String url = "jdbc:sqlite:C:/Users/uzeyr/OneDrive/Documents/sqlite/TaskDB.db";
    private static String user = "root";
    private static String password = "password";

    public Database() {

    }
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url,user,password);

        return connection;
    }
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
    public void closeStatement(Statement statement) throws SQLException{
        statement.close();;
    }
    public void closePreparedStatement(PreparedStatement ps) throws SQLException {
        ps.close();
    }
    public void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

}
