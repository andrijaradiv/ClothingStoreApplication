package clothing4you;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class JDBCTest {

    private JDBC jdbc;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        jdbc = new JDBC();
        JDBC.createTable("users", JDBC.createUserTable);
    }

    @AfterEach
    void tearDown() throws SQLException, ClassNotFoundException {
        Connection conn = JDBC.establishConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DROP TABLE test_users;");
        stmt.close();
        conn.close();
    }

    @Test
    void establishConnection() throws SQLException, ClassNotFoundException {
        Connection conn = JDBC.establishConnection();
        assertNotNull(conn);
        conn.close();
    }

    @Test
    void createTable() throws SQLException, ClassNotFoundException {
        Connection conn = JDBC.establishConnection();
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='test_users';");
        assertTrue(res.next());
        stmt.close();
        conn.close();
    }

    @Test
    void insertUser() throws SQLException, ClassNotFoundException {
        Connection conn = JDBC.establishConnection();
        JDBC.insertUser(conn, "test", "test@example.com", "test123", "password", "test_users");
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM test_users WHERE username='test123';");
        assertTrue(res.next());
        assertEquals("test", res.getString("first_name"));
        assertEquals("test@example.com", res.getString("email"));
        assertEquals("test123", res.getString("username"));
        assertEquals("password", res.getString("password"));
        stmt.close();
        conn.close();
    }

    @Test
    void query() throws SQLException, ClassNotFoundException {
        Connection conn = JDBC.establishConnection();
        JDBC.insertUser(conn, "test", "test@example.com", "test123", "password", "test_users");
        ArrayList<String> result = JDBC.query("test_users", "username");
        assertTrue(result.contains("test test@example.com test123 password"));
    }

    @Test
    void exists() throws SQLException, ClassNotFoundException {
        Connection conn = JDBC.establishConnection();
        JDBC.insertUser(conn, "test", "test@example.com", "test123", "password", "test_users");
        assertTrue(JDBC.exists("test123", "test_users", "username"));
        assertFalse(JDBC.exists("nonexistent", "test_users", "username"));
    }
}
