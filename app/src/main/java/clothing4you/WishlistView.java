package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

//public class WishlistPage extends JDialog {
//    private final JPanel wishlistPanel;
//    private ArrayList<Item> items;
//    private JTable table;
//    private DefaultTableModel model;
//    private WishList wishList; //
//    private Cart cart;
//    private Catalog previousCatalog;
//
//
//    public WishlistPage(JFrame parent, ArrayList<Item> items, Catalog previousCatalog){
//        super(parent);
//        setTitle("My WishList");
//        wishlistPanel = new JPanel(new BorderLayout());
//        setContentPane(wishlistPanel);
//        setMinimumSize(new Dimension(600, 600));
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        setModal(true);
//        setLocationRelativeTo(parent);
//
//        this.previousCatalog = previousCatalog;
//        this.items = items;
//        cart = new Cart();
//        wishList = new WishList();//
//
//        model = new DefaultTableModel(new Object[]{"Name", "Price", "Category"}, 0);
//        table = new JTable(model);
//        JScrollPane scrollPane = new JScrollPane(table);
//        wishlistPanel.add(scrollPane, BorderLayout.CENTER);
//        table.setDefaultEditor(Object.class, null);
//
//        addItemsToTable();
//
//        JPanel button = createButtonPanel();
//        wishlistPanel.add(button, BorderLayout.SOUTH);
//
//        setVisible(true);
//    }
//
//    // Overloaded constructor
//    public WishlistPage(JFrame parent, ArrayList<Item> items) throws SQLException, ClassNotFoundException {
//        this(parent, items, new Catalog(parent));
//    }
//
//    private void addItemsToTable() {
//        for (Item item : items) {
//            model.addRow(new Object[]{item.getName(), item.getPrice(), item.getCategory()});
//        }
//    }
//
//    private JPanel createButtonPanel() {
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//
//        // Create and add back button
//        JButton backButton = createBackButton();
//        buttonPanel.add(backButton);
//
//        // Create and add add-to-cart button
//        JButton addToCartButton = createAddToCartButton();
//        buttonPanel.add(addToCartButton);
//
//        // Create and add checkout button
//        JButton checkoutButton = createCheckoutButton();
//        buttonPanel.add(checkoutButton);
//
//        return buttonPanel;
//    }
//
//    private JButton createBackButton() {
//        JButton backButton = new JButton("Back");
//        backButton.addActionListener(e -> {
//            dispose();
//            previousCatalog.setVisible(true);
//        });
//        return backButton;
//    }
//
//    private JButton createAddToCartButton() {
//        JButton addToCartButton = new JButton("Add to Cart");
//        addToCartButton.addActionListener(e -> {
//            int row = table.getSelectedRow();
//            if (row != -1) {
//                Item item = items.get(row);
//                cart.addItem(item);
//                JOptionPane.showMessageDialog(wishlistPanel, item.getName() + " added to cart.");
//            } else {
//                JOptionPane.showMessageDialog(wishlistPanel, "Please select an item that is available.");
//            }
//        });
//        return addToCartButton;
//    }
//
//    private JButton createCheckoutButton() {
//        JButton checkoutButton = new JButton("Checkout");
//        checkoutButton.addActionListener(e -> {
//            dispose();
//            OrderSummary mySummary = new OrderSummary(WishlistPage.this, cart.getItems());
//        });
//        return checkoutButton;
//    }
//
//}
// old name of the class = WishlistPage.java
public class WishlistView extends JDialog{
    private JPanel wishlistPanel;
    private JTable table;
    private DefaultTableModel model;
    private JButton backButton;
    private JButton addToCartButton;
    private JButton removeButton;
    private Catalog previousCatalog;
    //private WishlistController controller;

    public WishlistView(JFrame parent, ArrayList<Item> items, Catalog previousCatalog){//, WishlistController controller) {
        super(parent);
        setTitle("My WishList");
        wishlistPanel = new JPanel(new BorderLayout());
        setContentPane(wishlistPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);

        model = new DefaultTableModel(new Object[]{"Name", "Price", "Category"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        wishlistPanel.add(scrollPane, BorderLayout.CENTER);
        table.setDefaultEditor(Object.class, null);

        this.previousCatalog = previousCatalog;
       // this.controller = controller;

        backButton = new JButton("Back");
        addToCartButton = new JButton("Add to Cart");
        removeButton = new JButton("Remove");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        buttonPanel.add(addToCartButton);
        buttonPanel.add(removeButton);

        wishlistPanel.add(buttonPanel, BorderLayout.SOUTH);

        addItemsToTable(items);
        setVisible(true);
    }

    public JPanel getPanel() {
        return wishlistPanel;
    }
    public JButton createBackButton() {
        return backButton;
    }

    public JButton createAddToCartButton() {
        return addToCartButton;
    }
    public JButton createRemoveButton(){
        return removeButton;
    }

    public int getSelectedIndex() {
        return table.getSelectedRow();
    }

    public Catalog getPreviousCatalog(){return previousCatalog;}

    public void removeSelectedRow() {
        model.removeRow(table.getSelectedRow());
    }

    private void addItemsToTable(ArrayList<Item> items) {
        for (Item item : items) {
            model.addRow(new Object[]{item.getName(), item.getPrice(), item.getCategory()});
        }
    }

//    private void addItemsToTable(WishlistModel model) {
//        for (Item item : model.getWishlistItems()) {
//            this.model.addRow(new Object[]{item.getName(), item.getPrice(), item.getCategory()});
//        }
//    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(wishlistPanel, message);
    }
//    public void dispose() {
//        Window window = SwingUtilities.getWindowAncestor(wishlistPanel);
//        window.dispose();
//    }

}
