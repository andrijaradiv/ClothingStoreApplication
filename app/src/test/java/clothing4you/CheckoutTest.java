package clothing4you;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private Checkout checkout;

    @BeforeEach
    void setUp() {
        JFrame parent = new JFrame();
        OrderSummary orderSummary = new OrderSummary(parent);
        checkout = new Checkout(parent, orderSummary);
    }

    @Test
    void testAuthorizePaymentWithValidInput() throws SQLException, ClassNotFoundException {
        JTextField cardNameField = checkout.tfCardName;
        JTextField cardNumField = checkout.tfCardNum;
        JTextField addressField = checkout.tfAddress;
        JTextField cvcField = checkout.tfCVC;
        JTextField expMonthField = checkout.tfExpirationMonth;

        cardNameField.setText("John Doe");
        cardNumField.setText("4111111111111111");
        addressField.setText("123 Main St");
        cvcField.setText("123");
        expMonthField.setText("12");

        assertFalse(checkout.isVisible());
    }

    @Test
    void testAuthorizePaymentWithInvalidInput() throws SQLException, ClassNotFoundException {
        JTextField cardNameField = checkout.tfCardName;
        JTextField cardNumField = checkout.tfCardNum;
        JTextField addressField = checkout.tfAddress;
        JTextField cvcField = checkout.tfCVC;
        JTextField expMonthField = checkout.tfExpirationMonth;

        cardNameField.setText("");
        cardNumField.setText("");
        addressField.setText("");
        cvcField.setText("");
        expMonthField.setText("");

        assertFalse(checkout.isVisible());
    }

}
