package clothing4you;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void testRegisterWithMatchingPasswords() {
        Register register = new Register(null);
        register.tfName.setText("Test User");
        register.tfUsername.setText("testuser");
        register.tfEmail.setText("testuser@example.com");
        register.pfPassword.setText("password");
        register.pfConfirmPass.setText("password");

        register.btnRegister.doClick();
        // check that the Login window is opened after successful registration
        assertTrue(register.isVisible());
        assertFalse(register.isDisplayable());
    }

    @Test
    public void testRegisterWithNonMatchingPasswords() {
        Register register = new Register(null);
        register.tfName.setText("Test User");
        register.tfUsername.setText("testuser");
        register.tfEmail.setText("testuser@example.com");
        register.pfPassword.setText("password");
        register.pfConfirmPass.setText("wrongpassword");

        register.btnRegister.doClick();
        // check that an error message is displayed
        assertTrue(register.isVisible());
        JOptionPane optionPane = getOptionPane(register);
        assertNotNull(optionPane);
        assertEquals("Password do not match", optionPane.getMessage());
    }

    @Test
    public void testRegisterWithEmptyFields() {
        Register register = new Register(null);
        register.tfName.setText("");
        register.tfUsername.setText("");
        register.tfEmail.setText("");
        register.pfPassword.setText("");
        register.pfConfirmPass.setText("");

        register.btnRegister.doClick();
        // check that an error message is displayed for each empty field
        assertTrue(register.isVisible());
        JOptionPane optionPane = getOptionPane(register);
        assertNotNull(optionPane);
        assertEquals("Please fill all fields", optionPane.getMessage());
    }

    private JOptionPane getOptionPane(Register register) {
        JOptionPane optionPane = null;
        for (java.awt.Component c : register.getComponents()) {
            if (c instanceof JOptionPane) {
                optionPane = (JOptionPane) c;
                break;
            }
        }
        return optionPane;
    }
}
