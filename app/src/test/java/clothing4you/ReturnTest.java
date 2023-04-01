package clothing4you;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReturnTest {

    private Return testReturn;

    @BeforeEach
    public void setup() {
        testReturn = new Return(new JFrame(), new ArrayList<Item>());
    }

    @Test
    public void testSuccessfulReturn() {
        // Test that a successful return returns the correct message
        testReturn.nameTF.setText("Shirt");
        testReturn.quantitySpinner.setValue(1);
        try {
            testReturn.successfulReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String expectedMessage = "Thank you for your return. Here is your partial refund off 50%: $9.99";
        assertEquals(expectedMessage, JOptionPane.getRootFrame().getComponents()[1].getName());
    }

    @Test
    public void testUnsuccessfulReturn() {
        // Test that an unsuccessful return returns the correct message
        testReturn.nameTF.setText("Invalid Item");
        testReturn.quantitySpinner.setValue(1);
        try {
            testReturn.successfulReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String expectedMessage = "The item you entered does not exist.";
        assertEquals(expectedMessage, JOptionPane.getRootFrame().getComponents()[1].getName());
    }

    @Test
    public void testItemExist() throws SQLException, ClassNotFoundException {
        // Test that the itemExist method returns true when the item exists in the catalog
        assertTrue(JDBC.exists("Shirt", "catalog", "name"));
    }

    @Test
    public void testItemDoesNotExist() throws SQLException, ClassNotFoundException {
        // Test that the itemExist method returns false when the item does not exist in the catalog
        assertFalse(JDBC.exists("Invalid Item", "catalog", "name"));
    }
}
