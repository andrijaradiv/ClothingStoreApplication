package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderSummary extends JDialog{
    private JPanel orderSummaryPanel;
    private ArrayList<Item> items;
    private JTable table;
    private DefaultTableModel model;
    private Cart cart;
    private Catalog previousCatalog;

    public OrderSummary(JFrame parent, ArrayList<Item> items, Catalog previousCatalog){
        super(parent);
        setTitle("Order Summary");
        orderSummaryPanel = new JPanel(new BorderLayout());
        setContentPane(orderSummaryPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);
        this.previousCatalog = previousCatalog;
        this.items = items;
        cart = new Cart();

        String[] column = {"Name", "Price"};
        model = new DefaultTableModel(column, 0);

        table = new JTable(model);
        int totalQuantity = 0;
        for (Item item: items){
            cart.addItem(item);
            model.addRow(new Object[]{item.getName(), "$" + String.format("%.2f",item.getPrice())});
        }
        model.addRow(new Object[]{"______________________"});
        model.addRow(new Object[]{"Subtotal:", "$" + String.format("%.2f",cart.getSubTotal())});
        model.addRow(new Object[]{"Tax:", "$" + String.format("%.2f",cart.getTax())});
        model.addRow(new Object[]{"______________________"});
        model.addRow(new Object[]{"Total:", "$" + String.format("%.2f",cart.getTotal())});

        orderSummaryPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        table.setDefaultEditor(Object.class, null);

        JPanel button = new JPanel();
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

        JButton checkout = new JButton("CheckOut");
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Checkout myCheckout = new Checkout(null, OrderSummary.this);
            }
        });
        button.add(checkout);
        orderSummaryPanel.add(button, BorderLayout.SOUTH);

        setVisible(true);
    }

    //getter

    public Cart getCart() {
        return cart;
    }
    
    // Overloaded constructor
    public OrderSummary(JFrame parent, ArrayList<Item> items) throws SQLException, ClassNotFoundException {
        // implementation
        this(parent, items, new Catalog(parent));
    }
}



