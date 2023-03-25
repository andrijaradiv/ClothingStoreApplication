package clothing4you;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static clothing4you.JDBC.exists;
import static clothing4you.JDBC.query;

public class Return extends JDialog{
    private JPanel returnPanel;
    private JSpinner quantitySpinner;
    private JTextField returnTF;
    private JTextField nameTF;
    private JButton submitBtn;
    private JButton cancelBtn;
    private ArrayList<Item> items;
    private Cart cart;

    public Return(JFrame parent, ArrayList<Item> items){
        super(parent);
        setTitle("Return");
        setContentPane(returnPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);

        this.items = items;
        cart = new Cart();

        quantitySpinner.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    successfulReturn();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    Catalog myCatalog = new Catalog(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }

    public void successfulReturn() throws SQLException, ClassNotFoundException {
        String itemName = nameTF.getText();
        int quantity = (int) quantitySpinner.getValue();
        boolean itemExist = exists(itemName, "catalog", "name");
        double price = 0;
        ArrayList result = query("catalog", "");

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).toString().contains(itemName)) {
                String[] splited = result.get(i).toString().split(" ");
                price = Double.parseDouble(splited[4]);
                // perform the return submission for this item
                break;
            }
        }

        price = price * 0.5;

        if (itemExist) {
            JOptionPane.showMessageDialog(null, "Thank you for your return. Here is your partial refund off 50%: $"+price);

            // preform the return submission
        } else {
            JOptionPane.showMessageDialog(null, "The item you entered does not exist.");
        }
    }

}

