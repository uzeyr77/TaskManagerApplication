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
        String sql = "INSERT INTO taskRecord (ID, Description, Status, DateCreated, DateUpdated) VALUES (?, ?, ?, ?, ?)";
        try(
            Connection con = this.dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
            pstmt.setString(1,task.getId());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3,TaskStatus.taskStatusToString(task.getStatus()));
            pstmt.setString(4, task.getDateCreated().toString());
            int result = pstmt.executeUpdate();
            return result != 0;
        }
    }
    public boolean checkById(String id) throws SQLException{
        //int result = 0;
            String sql = "SELECT * FROM taskRecord WHERE ID=?";

            try(
                Connection con = this.dataSource.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ) {
                pstmt.setString(1,id);
                ResultSet rs = pstmt.executeQuery();
                return rs.next();
            }
    }

    public boolean update(String id, String newDesc) throws SQLException {
        String sql = "UPDATE taskRecord SET Description = ? , DateUpdated = ? WHERE ID = ?"; //UPDATE my_table SET columnName, columnName, WHERE [condition]
        try(
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
            int result = 0;
            pstmt.setString(1, newDesc);
            pstmt.setString(2, LocalDate.now().toString());
            pstmt.setString(3, id);
            result = pstmt.executeUpdate(); // preparedStatement.executeUpdate() method returns the # of rows effected
            return result != 0;
        }
    }
    public boolean update(String id, TaskStatus newStatus) throws SQLException{
        String sql = "UPDATE taskRecord SET Status = ?, DateUpdated = ? WHERE ID= ?"; // updating the description
        int result = 0;
        try (
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
             ) {
            pstmt.setString(1, TaskStatus.taskStatusToString(newStatus)); // sets the first "?" (status)
            pstmt.setString(2, LocalDate.now().toString()); // sets the second "?" (date)
            pstmt.setString(3, id); // sets the third "?" (ID)
            result = pstmt.executeUpdate(); // rows affected
            return result != 0;
        }

    }
    public boolean delete(String id) throws SQLException{
        int result = 0;
        String sql = "DELETE FROM taskRecord WHERE ID = ?";
        try(
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
            pstmt.setString(1, id);
            result = pstmt.executeUpdate();
            return result != 0;
        }
    }
    public Optional<Task> get(String id) throws SQLException {
        Task task = null;
        String sql = "SELECT ID, Description, Status, DateCreated, DateUpdated FROM taskRecord WHERE ID= ?"; // SELECT column_name FROM your_table_name WHERE id = ?"
        try (
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            ) {
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.getString("Status") == null || rs.getString("ID") == null || rs.getString("Description") == null) {
                return Optional.empty();
            }
            task = resultSetToTask(id, rs.getString("Description"), rs.getString("Status"), rs.getString("DateCreated"),rs.getString("DateUpdated"));


            return Optional.ofNullable(task);
        }
    }

    public List<Task> findAll() throws SQLException {
        List<Task> taskList = new ArrayList<>();
        Task task;
        String sql = "SELECT * FROM taskRecord"; //  * means all"
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                ) {
            while (rs.next()) {
                task = resultSetToTask(rs.getString("ID"), rs.getString("Description"), rs.getString("Status"), rs.getString("DateCreated"),rs.getString("DateUpdated"));
                taskList.add(task);
            }
            return taskList;
        }

    }
    public Optional<Task> getByDescription(String desc) throws SQLException {
        Task task = null;
        String sql = "SELECT ID, Status, DateCreated, DateUpdated FROM taskRecord WHERE Description= ?"; // SELECT column_name FROM your_table_name WHERE description = ?"
        try(
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
            pstmt.setString(1,desc);
            ResultSet rs = pstmt.executeQuery();
            if(rs.getString("Status") == null || rs.getString("ID") == null || desc == null) {
                return Optional.empty();
            }
            task = resultSetToTask(rs.getString("ID"), desc, rs.getString("Status"), rs.getString("DateCreated"),rs.getString("DateUpdated"));
            return Optional.ofNullable(task);
        }
    }

    public List<Task> getAllByStatus(TaskStatus status) throws SQLException {
        String sql = "SELECT * FROM taskRecord WHERE Status= ?"; //  * means all condition = by status
        List<Task> taskList = new ArrayList<>();
        Task task;
        try(
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
            pstmt.setString(1,TaskStatus.taskStatusToString(status));
            ResultSet rs = pstmt.executeQuery();
            if(rs.getString("Status") == null || rs.getString("ID") == null || rs.getString("Description") == null) {
                return taskList;
            }
            while (rs.next()) {
                task = new Task(rs.getString("ID"), rs.getString("Description"), status);
                task.setDateCreated(LocalDate.parse(rs.getString("DateCreated")));
                if(rs.getString("DateUpdated") == null) {
                    task.setDateUpdated(null);
                } else {
                    task.setDateUpdated(LocalDate.parse(rs.getString("DateUpdated")));
                }
                taskList.add(task);
            }
            return taskList;
        }
    }
    public boolean deleteAll() throws SQLException {
        int result = 0;
        String sql = "DELETE FROM taskRecord";
        try(
                Connection con = dataSource.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            //pstmt.setString(1,);
            result = pstmt.executeUpdate();
            return true;
        }
    }

    private Task resultSetToTask(String id, String description, String status, String dateCreated, String dateUpdated) {
        Task task = null;
            if(status.equals("in progress")) {
                task = new Task(id, description, TaskStatus.IN_PROGRESS);
            } else {
                task = new Task(id, description, TaskStatus.valueOf(status.toUpperCase()));
            }
        task.setDateCreated(LocalDate.parse(dateCreated));
        if(dateUpdated == null) {
            task.setDateUpdated(null);
        } else {
            task.setDateUpdated(LocalDate.parse(dateUpdated));
        }
            return task;
    }

}
