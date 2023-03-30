package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishlistPage extends JDialog {
    private final JPanel wishlistPanel;
    private final ArrayList<Item> items;
    private final JTable table;
    private final DefaultTableModel model;
    private final WishList wishList; //
    //private final Cart cart;
    private final Catalog previousCatalog;


    public WishlistPage(JFrame parent, ArrayList<Item> items, Catalog previousCatalog){
        super(parent);
        setTitle("My WishList");
        wishlistPanel = new JPanel(new BorderLayout());
        setContentPane(wishlistPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);

        this.previousCatalog = previousCatalog;
        this.items = items;
        //cart = new Cart();
        wishList = new WishList();//

        model = new DefaultTableModel(new Object[]{"Name", "Price", "Category"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        wishlistPanel.add(scrollPane, BorderLayout.CENTER);
        table.setDefaultEditor(Object.class, null);

        addItemsToTable();

        JPanel button = createButtonPanel();
        wishlistPanel.add(button, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Overloaded constructor
    public WishlistPage(JFrame parent, ArrayList<Item> items) throws SQLException, ClassNotFoundException {
        this(parent, items, new Catalog(parent));
    }

    private void addItemsToTable() {
        for (Item item : items) {
            model.addRow(new Object[]{item.getName(), item.getPrice(), item.getCategory()});
        }
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create and add back button
        JButton backButton = createBackButton();
        buttonPanel.add(backButton);

        // Create and add add-to-cart button
        JButton addToCartButton = createAddToCartButton();
        buttonPanel.add(addToCartButton);

        // Create and add checkout button
        JButton checkoutButton = createCheckoutButton();
        buttonPanel.add(checkoutButton);

        return buttonPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            previousCatalog.setVisible(true);
        });
        return backButton;
    }

    private JButton createAddToCartButton() {
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Item item = items.get(row);
                Cart.addItem(item);
                JOptionPane.showMessageDialog(wishlistPanel, item.getName() + " added to cart.");
            } else {
                JOptionPane.showMessageDialog(wishlistPanel, "Please select an item that is available.");
            }
        });
        return addToCartButton;
    }

    private JButton createCheckoutButton() {
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            dispose();
            OrderSummary mySummary = new OrderSummary(null, previousCatalog);
        });
        return checkoutButton;
    }

}


