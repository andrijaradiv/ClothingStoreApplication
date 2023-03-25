package clothing4you;

import org.junit.Test;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class UserTest {
    @Test
    public void createNewUser() throws SQLException, ClassNotFoundException {
        String createTestTable = "CREATE TABLE IF NOT EXISTS test (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "first_name TEXT," +
                "email TEXT," +
                "username TEXT," +
                "password TEXT" +
                ");";
        JDBC.createTable("test", createTestTable);

        String name = "John Doe";
        String email = "johnDoe@my.yorku.ca";
        String username = "JohnDoe";
        String password = "JohnDoe";
        UserManager.register(name, email, username, password, "test");
        ArrayList res = (ArrayList) JDBC.database("select first_name from test where username==\"" + username + "\";");
        assertEquals(res.get(0), name);
    }
}
