package clothing4you;

import clothing4you.backend.JDBC;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JDBCTest {

    private static final String TEST_USER = "testuser";
    private static final String TEST_EMAIL = "testuser@example.com";
    private static final String TEST_PASSWORD = "testpass";
    private static final String TEST_ITEM_NAME = "Test Item";
    private static final String TEST_ITEM_CATEGORY = "Test Category";
    private static final String TEST_ITEM_SIZE = "Test Size";
    private static final int TEST_ITEM_QUANTITY = 1;
    private static final double TEST_ITEM_PRICE = 9.99;

    @BeforeClass
    public static void setUp() throws SQLException, ClassNotFoundException {
        JDBC.establishConnection();
        JDBC.createTable("users", JDBC.createUserTable);
        JDBC.createTable("catalog", JDBC.createCatalogTable);
    }

    @Test
    public void testConnection() throws SQLException, ClassNotFoundException {
        assertNotNull(JDBC.getConnection());
    }

    @Test
    public void testCreateTable() throws SQLException, ClassNotFoundException {
        JDBC.createTable("testtable", "CREATE TABLE IF NOT EXISTS testtable (ID INTEGER PRIMARY KEY)");
        ArrayList result = JDBC.query("testtable", "ID");
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testInsertUser() throws SQLException, ClassNotFoundException {
        JDBC.insertUser(TEST_USER, TEST_EMAIL, TEST_USER, TEST_PASSWORD, "users");
        assertTrue(JDBC.exists(TEST_USER, "users", "username"));
    }

    @Test
    public void testInsertItem() throws SQLException, ClassNotFoundException {
        JDBC.insertItem(TEST_ITEM_NAME, TEST_ITEM_CATEGORY, TEST_ITEM_SIZE, TEST_ITEM_QUANTITY, TEST_ITEM_PRICE);
        ArrayList result = JDBC.query("catalog", "name");
        assertNotNull(result);
        assertTrue(result.contains(TEST_ITEM_NAME + " " + TEST_ITEM_CATEGORY + " " + TEST_ITEM_SIZE + " " + TEST_ITEM_QUANTITY + " " + TEST_ITEM_PRICE));
    }

    @Test
    public void testQuery() throws SQLException, ClassNotFoundException {
        ArrayList result = JDBC.query("users", "username");
        assertNotNull(result);

    }

    @Test
    public void testExists() throws SQLException, ClassNotFoundException {
        assertTrue(JDBC.exists(TEST_USER, "users", "username"));
        assertFalse(JDBC.exists("nonexistent", "users", "username"));
    }
}
