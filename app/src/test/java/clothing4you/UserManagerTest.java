package clothing4you;

import clothing4you.backend.JDBC;
import clothing4you.backend.UserManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {


    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        JDBC.establishConnection();
    }

    @AfterEach
    void tearDown() throws SQLException {
        JDBC.closeConnection();
    }

    @Test
    void registerNewUser() {
        String name = "Test User";
        String email = "testuser@example.com";
        String username = "testuser";
        String password = "password";

        // Register a new user
        UserManager.register(name, email, username, password);

        // Check if the user has been registered
        boolean userExists = false;
        try {
            userExists = JDBC.exists(username, "users", "username");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(userExists, "The user should be registered");
    }

    @Test
    void registerDuplicateUser() {
        String name = "Duplicate User";
        String email = "duplicateuser@example.com";
        String username = "duplicateuser";
        String password = "password";

        // Register the user twice
        UserManager.register(name, email, username, password);
        assertThrows(RuntimeException.class, () -> UserManager.register(name, email, username, password),
                "Registering a duplicate user should throw a RuntimeException");
    }

    @Test
    void registerEmptyName() {
        String name = "";
        String email = "emptyname@example.com";
        String username = "emptyname";
        String password = "password";

        assertThrows(RuntimeException.class, () -> UserManager.register(name, email, username, password),
                "Registering a user with an empty name should throw a RuntimeException");
    }

    @Test
    void registerEmptyEmail() {
        String name = "Empty Email";
        String email = "";
        String username = "emptyemail";
        String password = "password";

        assertThrows(RuntimeException.class, () -> UserManager.register(name, email, username, password),
                "Registering a user with an empty email should throw a RuntimeException");
    }

    @Test
    void registerEmptyUsername() {
        String name = "Empty Username";
        String email = "emptyusername@example.com";
        String username = "";
        String password = "password";

        assertThrows(RuntimeException.class, () -> UserManager.register(name, email, username, password),
                "Registering a user with an empty username should throw a RuntimeException");
    }
}
