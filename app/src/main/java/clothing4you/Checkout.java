package clothing4you;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public Checkout(JFrame parent) {
        super(parent);
        setTitle("Checkout");
        setContentPane(checkoutPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //go back to the catalog page
                Catalog myCatalog = new Catalog(null);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add card info to the database - insert into user profile
                //verify the details
                //printout a msg
                authorizePayment();
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

    private void authorizePayment() {
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
            int choice = JOptionPane.showConfirmDialog(this, "Would you like to continue browsing?","", JOptionPane.YES_NO_OPTION);
            dispose();
            if(choice == JOptionPane.YES_OPTION) {
                Catalog myCatalog = new Catalog(null);
            }
        }
    }

    //main method to test it
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        Checkout mycheckout = new Checkout(null);
    }

}
