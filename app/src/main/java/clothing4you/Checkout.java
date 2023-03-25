package clothing4you;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.formdev.flatlaf.FlatDarculaLaf;

public class Checkout extends JDialog {
    private JTextField tfCardName;
    private JTextField tfCardNum;
    private JTextField tfAddress;
    private JTextField tfCVC;
    private JTextField tfExpirationMonth;
    private JButton cancelButton;
    private JButton submitButton;
    private JPanel checkoutPanel;
    private JTextField tfPromoCode;
    private JButton applyButton;
    private JLabel iconLabel1;
    private JLabel iconLabel2;
    private JLabel iconLabel3;
    private JLabel totalLabel;
    private OrderSummary previousSummary;

    ImageIcon icon1 = new ImageIcon("img/visaNew.png");
    ImageIcon icon2 = new ImageIcon("img/amex.png");
    ImageIcon icon3 = new ImageIcon("img/paypal.png");





    public Checkout(JFrame parent, OrderSummary previousSummary) {
        super(parent);
        setTitle("Checkout");
        setContentPane(checkoutPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);
        this.previousSummary = previousSummary;
        iconLabel1.setText("");
        iconLabel1.setIcon(icon1);
        iconLabel2.setText("");
        iconLabel2.setIcon(icon2);
        iconLabel3.setText("");
        iconLabel3.setIcon(icon3);

        Cart cart = previousSummary.getCart();
        double cartTotal = cart.getTotal();
        totalLabel.setText("Total: " + " " + String.format("%.2f",cartTotal));

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //go back to the summary page
                previousSummary.setVisible(true);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add card info to the database - insert into user profile
                //verify the details
                //printout a msg
                try {
                    authorizePayment();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //based on the promo code: eg 15OFF , 20OFF
                //subtract 15% of the subtotal we get from the order summary
                //reflect new subtotal
            }
        });

        setVisible(true);
    }

    private void authorizePayment() throws SQLException, ClassNotFoundException {
        String cardName = tfCardName.getText();
        String cardNum = String.valueOf(tfCardNum.getText());
        String billAddress = tfAddress.getText();
        String cvc = String.valueOf(tfCVC.getText());
        String expirationMonth = tfExpirationMonth.getText();
        //String expirationYear =  tfExpirationYear.getText();

        if (cardNum.isEmpty() || cardName.isEmpty() || billAddress.isEmpty() || cvc.isEmpty() || expirationMonth.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        // if credit card details verified successfully, print msg "Payment Successful!"
        else{
            JOptionPane.showMessageDialog(this, "Payment Successful!");
            int choice = JOptionPane.showConfirmDialog(this,
                    "Would you like to continue browsing?",
                    "", JOptionPane.YES_NO_OPTION);
            dispose();
            if(choice == JOptionPane.YES_OPTION) {
                Catalog myCatalog = new Catalog(null);
            }
        }
    }

    //main method to test it
    /*public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        Checkout mycheckout = new Checkout(null);
    }*/

}
