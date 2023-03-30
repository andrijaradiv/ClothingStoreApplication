package clothing4you;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;
    private JFrame frame;

    @BeforeEach
    public void setUp() {
        frame = new JFrame();
        login = new Login(frame);
        login.setVisible(true);
    }

    @Test
    public void testLoginWithValidCredentials() throws SQLException, ClassNotFoundException {
        // Set up valid credentials
        login.tfUsername.setText("testuser");
        login.pfPassword.setText("testpassword");

        // Trigger the login button
        login.btnLogin.doClick();

        // Verify that the catalog window is displayed
        Component[] components = frame.getComponents();
        boolean catalogWindowFound = false;
        for (Component component : components) {
            if (component instanceof Catalog) {
                catalogWindowFound = true;
                break;
            }
        }
        assertTrue(catalogWindowFound, "Catalog window not found after logging in with valid credentials");
    }

    @Test
    public void testLoginWithInvalidCredentials() throws SQLException, ClassNotFoundException {
        // Set up invalid credentials
        login.tfUsername.setText("invaliduser");
        login.pfPassword.setText("invalidpassword");

        // Trigger the login button
        login.btnLogin.doClick();

        // Verify that an error message is displayed
        Frame[] frames = JFrame.getFrames();
        for (Frame frame : frames) {
            Component[] components = frame.getComponents();
            for (Component component : components) {
                if (component instanceof JOptionPane) {
                    JOptionPane optionPane = (JOptionPane) component;
                    assertEquals(JOptionPane.ERROR_MESSAGE, optionPane.getMessageType(),
                            "Error message not displayed when logging in with invalid credentials");
                    return;
                }
            }
        }

        fail("Error message not displayed when logging in with invalid credentials");
    }

    @Test
    public void testGuestLogin() throws SQLException, ClassNotFoundException {
        // Trigger the guest login button
        login.btnGuest.doClick();

        // Verify that the catalog window is displayed
        Component[] components = frame.getComponents();
        boolean catalogWindowFound = false;
        for (Component component : components) {
            if (component instanceof Catalog) {
                catalogWindowFound = true;
                break;
            }
        }
        assertTrue(catalogWindowFound, "Catalog window not found after logging in as a guest");
    }

    @Test
    public void testRegisterButton() {
        // Trigger the register button
        login.btnRegister.doClick();

        // Verify that the register window is displayed
        Component[] components = frame.getComponents();
        boolean registerWindowFound = false;
        for (Component component : components) {
            if (component instanceof Register) {
                registerWindowFound = true;
                break;
            }
        }
        assertTrue(registerWindowFound, "Register window not found after clicking the register button");
    }

    @Test
    public void testJobButton() {
        // Trigger the job button
        login.btnJob.doClick();

        // Verify that the job window is displayed
        Component[] components = frame.getComponents();
        boolean jobWindowFound = false;
        for (Component component : components) {
            if (component instanceof Job) {
                jobWindowFound = true;
                break;
            }
        }
        assertTrue(jobWindowFound, "Job window not found after clicking the job button");
    }
}
