package clothing4you;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserManagerTest {
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        JDBC.createTable("users", JDBC.createUserTable);
        JDBC.createTable("catalog", JDBC.createCatalogTable);
    }

    @After
    public void tearDown() throws SQLException, ClassNotFoundException {
        JDBC.createTable("users", JDBC.createUserTable);
        JDBC.createTable("catalog", JDBC.createCatalogTable);
    }

    @Test
    public void testEstablishConnection() throws SQLException, ClassNotFoundException {
        assertNotNull(JDBC.getConnection());
    }

    @Test
    public void testCreateTable() throws SQLException, ClassNotFoundException {
        JDBC.createTable("test", "CREATE TABLE IF NOT EXISTS test (id INTEGER PRIMARY KEY)");
        ArrayList<String> result = JDBC.query("test", "");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testInsertUser() throws SQLException, ClassNotFoundException {
        JDBC.insertUser("Test", "test@example.com", "testuser", "testpassword");
        ArrayList<String> result = JDBC.query("users", "first_name");
        assertTrue(result.contains("Test"));
    }

    @Test
    public void testInsertItem() throws SQLException, ClassNotFoundException {
        JDBC.insertItem("T-shirt", "Tops", "M", 1, 20.00);
        ArrayList<String> result = JDBC.query("catalog", "name");
        assertTrue(result.contains("T-shirt"));
    }

    @Test
    public void testQuery() throws SQLException, ClassNotFoundException {
        JDBC.insertUser("Test", "test@example.com", "testuser", "testpassword");
        ArrayList<String> result = JDBC.query("users", "first_name");
        assertTrue(result.contains("Test"));
    }

    @Test
    public void testExists() throws SQLException, ClassNotFoundException {
        JDBC.insertUser("Test", "test@example.com", "testuser", "testpassword");
        assertTrue(JDBC.exists("testuser", "users", "username"));
        assertFalse(JDBC.exists("testuser2", "users", "username"));
    }
}
