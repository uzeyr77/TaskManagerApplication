package Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAO {
    // dependencies
    Database dataSource;
    public TaskDAO(Database dataSource) {
        this.dataSource = dataSource;
    }
    public boolean save(Task task) throws SQLException{
        if(task == null) throw new RuntimeException("CANNOT INSERT NULL TASK");
        Connection con = this.dataSource.getConnection();
        String sql = "INSERT INTO tasks (ID, Description, Status, DateCreated) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,task.getId());
        pstmt.setString(2, task.getDescription());
        pstmt.setString(3,task.getStatus().toString().toLowerCase().replace("_", " "));
        pstmt.setString(4, task.getCreatedAt().toString());
        int result = pstmt.executeUpdate();
        dataSource.closeConnection(con);
        dataSource.closePreparedStatement(pstmt);
        return result != 0;
    }
    public List<Task> findAll() {
        return null;
    }
    public boolean update(String id, String newDesc) throws SQLException {
        if(id == null || id.length() < 5 || newDesc == null) throw new RuntimeException("INVALID ID, CANT GET TASK DESCRIPTION");
        Connection con = dataSource.getConnection();
        int result = 0;
        String sql = "UPDATE tasks SET Description = ? , UpdatedAt = ? WHERE ID = ?"; //UPDATE my_table SET columnName, columnName, WHERE [condition]
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, newDesc);
        pstmt.setString(2, LocalDate.now().toString());
        pstmt.setString(3, id);
        result = pstmt.executeUpdate(); // preparedStatement.executeUpdate() method returns the # of rows effected

        dataSource.closeConnection(con);
        dataSource.closePreparedStatement(pstmt);
        return result != 0; //
    }
    public boolean update(String id, TaskStatus newStatus) throws SQLException{
        if(id == null || id.length() < 5 || newStatus == null) throw new RuntimeException("INVALID ID, CANT UPDATE TASK STATUS");
        Connection con = dataSource.getConnection();
        String sql = "UPDATE tasks SET Status = ?, UpdatedAt = ? WHERE ID= ?"; // updating the description
        int result = 0;
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, newStatus.toString().toLowerCase().replace("_", " ")); // sets the first "?" (status)
        pstmt.setString(2, LocalDate.now().toString()); // sets the second "?" (date)
        pstmt.setString(3, id); // sets the third "?" (ID)
        result = pstmt.executeUpdate(); // rows affected
        dataSource.closeConnection(con);
        dataSource.closePreparedStatement(pstmt);
        return result != 0;
    }
    public boolean delete(String id) throws SQLException{
        if(id == null || id.length() < 5) throw new RuntimeException("INVALID ID, CANT DELETE TASK");
        int result = 0;
        String sql = "DELETE FROM tasks WHERE ID = ?";
        Connection con = dataSource.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        result = pstmt.executeUpdate();
        dataSource.closePreparedStatement(pstmt);
        return result != 0;
    }
    public Task getByID(String id) throws SQLException {
        if(id == null || id.length() < 5) throw new RuntimeException("INVALID ID, CANT GET TASK");
        Task result = null;
        Connection con = dataSource.getConnection();
        String sql = "SELECT ID, Description, Status, DateCreated FROM tasks WHERE ID= ?"; // SELECT column_name FROM your_table_name WHERE id = ?"
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String desc = rs.getString("Description");
                if(rs.getString("Status").equals("in progress")) {
                    result = new Task(desc, TaskStatus.IN_PROGRESS);
                } else {
                    result = new Task(desc, TaskStatus.valueOf(rs.getString("Status").toUpperCase()));
                }
                result.setId(id);
            }
        Optional<Task> opt = Optional.ofNullable(result);
        dataSource.closeConnection(con);
        dataSource.closePreparedStatement(pstmt);
        dataSource.closeResultSet(rs);

        return opt.orElse(new Task("UNKNOWN", TaskStatus.NONE)); // return Optional<Task.Task>
    }

    public List<Task> getAll() throws SQLException {
        List<Task> taskList = new ArrayList<>();
        Task task;
        Connection con = dataSource.getConnection();
        String sql = "SELECT * FROM tasks"; //  * means all"
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String id = rs.getString("ID");
            String desc = rs.getString("Description");
            String status = rs.getString("Status");
            String dateCreated = rs.getString("DateCreated");
            String dateUpdated = rs.getString("UpdatedAt");
            if (status.equals("in progress")) {
                task = new Task(desc, TaskStatus.IN_PROGRESS);
            } else {
                task = new Task(desc, TaskStatus.valueOf(status.toUpperCase()));
            }
            task.setId(id);
            task.setCreatedAt(LocalDate.parse(dateCreated));
            if(dateUpdated != null) {
                task.setUpdatedAt(LocalDate.parse(dateUpdated));
            } else {
                task.setUpdatedAt(null);
            }
                taskList.add(task);
        }

            dataSource.closeResultSet(rs);
            dataSource.closeStatement(pstmt);
            dataSource.closeConnection(con);
            return taskList;
    }
    public Task getByDescription(String desc) throws SQLException {
        if(desc == null || desc.isEmpty()) throw new RuntimeException("EMPTY DESCRIPTION");
        Task result = null;
        Connection con = dataSource.getConnection();
        String sql = "SELECT ID, Description, Status, DateCreated FROM tasks WHERE Description= ?"; // SELECT column_name FROM your_table_name WHERE description = ?"
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,desc);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            System.out.println(rs.next());
            String id = rs.getString("ID");
            if(rs.getString("Status").equals("in progress")) {
                result = new Task(desc, TaskStatus.IN_PROGRESS);
            } else {
                result = new Task(desc, TaskStatus.valueOf(rs.getString("Status").toUpperCase()));
            }
            result.setId(id);
        }
        Optional<Task> opt = Optional.ofNullable(result);
        dataSource.closeConnection(con);
        dataSource.closePreparedStatement(pstmt);
        dataSource.closeResultSet(rs);
        return opt.orElse(new Task("UNKNOWN", TaskStatus.NONE));
    }

    public List<Task> getAllByStatus(TaskStatus status) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        Task task;
        Connection con = dataSource.getConnection();
        String sql = "SELECT * FROM tasks WHERE Status= ?"; //  * means all condition = by status
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,status.toString().toLowerCase().replace("_"," "));
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String id = rs.getString("ID");
            String desc = rs.getString("Description");
            String dateCreated = rs.getString("DateCreated");
            String dateUpdated = rs.getString("UpdatedAt");
            if (status.toString().equals("in progress")) {
                task = new Task(desc, TaskStatus.IN_PROGRESS);
            } else {
                task = new Task(desc, status);
            }
            task.setId(id);
            task.setCreatedAt(LocalDate.parse(dateCreated));
            if(dateUpdated != null) {
                task.setUpdatedAt(LocalDate.parse(dateUpdated));
            } else {
                task.setUpdatedAt(null);
            }
            taskList.add(task);
        }

        return taskList;
    }

}
