package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
//this is a page that contains the items a user added to its wishlist
public class WishlistPage extends JDialog {
    private final JPanel wishlistPanel;
    private final ArrayList<Item> items;
    private final JTable table;
    private final DefaultTableModel model;

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
    //add items to the table 
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

        // Create and add remove button
        JButton removeButton = createRemoveButton();
        buttonPanel.add(removeButton);

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
    //add to cart an item and removes iut from the wishlist
    private JButton createAddToCartButton() {
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Item item = items.get(row);
                Cart.addItem(item);
                JOptionPane.showMessageDialog(wishlistPanel, item.getName() + " added to cart.");
                WishList.removeItem(item);
                model.removeRow(row);
                table.setModel(model);
            } else {
                JOptionPane.showMessageDialog(wishlistPanel, "Please select an item that is available.");
            }
        });
        return addToCartButton;
    }
    //removes and item from the wishlist if you dont like it anymore  
    private JButton createRemoveButton() {
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Item item = items.get(row);
                WishList.removeItem(item);
                JOptionPane.showMessageDialog(wishlistPanel, item.getName() + " removed from the wish list.");
                model.removeRow(row);
                table.setModel(model);
            } else {
                JOptionPane.showMessageDialog(wishlistPanel, "Please select an item that is available.");
            }
        });
        return removeButton;
    }
    //if you want to checkout you can jump to checkout after you add your items to the cart 
    private JButton createCheckoutButton() {
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            dispose();
            OrderSummary mySummary = new OrderSummary(null, previousCatalog);
        });
        return checkoutButton;
    }

}


