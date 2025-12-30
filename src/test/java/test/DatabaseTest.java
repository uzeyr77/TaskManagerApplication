package test;

import Task.Database;
import Task.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database db;
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    @BeforeEach
    void setUp() throws SQLException {
        db = new Database();
        con = db.getConnection();

    }
    @AfterEach
    void close() throws SQLException {
        db.closeConnection(con);

    }
    @Test
    void test_getConnection() throws SQLException {
        assertNotNull(con);
    }
    @Test
    void test_closeConnection() throws SQLException {
        db.closeConnection(con);
        assertTrue(con.isClosed());
    }

}