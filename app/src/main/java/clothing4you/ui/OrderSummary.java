package clothing4you.ui;

import clothing4you.backend.Cart;
import clothing4you.backend.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

//this is a gui page that will contain the order summary when you want to checkout 
public class OrderSummary extends JDialog{
    private JPanel orderSummaryPanel;
    private ArrayList<Item> items;
    private JTable table;
    private DefaultTableModel model;
    //private Cart cart;
    private Catalog previousCatalog;
    private WishlistPage previousWishlistPage;

    public OrderSummary(JFrame parent,/* ArrayList<Item> items,*/ Catalog previousCatalog){
        super(parent);
        setTitle("Order Summary");
        orderSummaryPanel = new JPanel(new BorderLayout());
        setContentPane(orderSummaryPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);
        this.previousCatalog = previousCatalog;

        String[] column = {"Name", "Price"};
        model = new DefaultTableModel(column, 0);

        table = new JTable(model);
        int totalQuantity = 0;

        createTotalRows();


        orderSummaryPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        table.setDefaultEditor(Object.class, null);

        //creates a panel fornthe buttons 
        JPanel button = new JPanel();
        //back button
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //Catalog myCatalog = new Catalog(null);
                previousCatalog.setVisible(true);
            }
        });
        button.add(back);
        //checkout button
        JButton checkout = new JButton("CheckOut");
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Checkout myCheckout = new Checkout(null, OrderSummary.this);
            }
        });
        button.add(checkout);
        //remove button
        JButton remove = new JButton("Remove Item");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Item item = Cart.getItems().get(row);
                    Cart.removeItem(item);
                    JOptionPane.showMessageDialog(orderSummaryPanel, item.getName() + " removed from the Cart.");
                    model.removeRow(row);
                    model.setRowCount(0);
                    createTotalRows();
                } else {
                    JOptionPane.showMessageDialog(orderSummaryPanel, "Please select an item that is available.");
                }
            }
        });
        button.add(remove);
        orderSummaryPanel.add(button, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Overloaded constructor
    public OrderSummary(JFrame parent, WishlistPage previousWishlistPage) throws SQLException, ClassNotFoundException {
        // implementation
        this(parent, /*items,*/ new Catalog(parent));
    }
    
    //this is how the order summary would be displayed 
    private void createTotalRows(){
        Cart.getItems().forEach(item -> {
            model.addRow(new Object[]{item.getName(), "$" + String.format("%.2f", item.getPrice())});
        });
        model.addRow(new Object[]{"______________________"});
        model.addRow(new Object[]{"Subtotal:", "$" + String.format("%.2f", Cart.getSubTotal())});
        model.addRow(new Object[]{"Tax:", "$" + String.format("%.2f", Cart.getTax())});
        model.addRow(new Object[]{"______________________"});
        model.addRow(new Object[]{"Total:", "$" + String.format("%.2f", Cart.getTotal())});
    }

}



