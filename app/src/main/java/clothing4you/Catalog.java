package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static clothing4you.JDBC.query;

// This is a class called Catalog, which represents a catalog page of a clothing store.
public class Catalog extends JDialog {
    private JPanel catalogPanel;
    // It also contains a JComboBox called "cmCategory" which represents a drop-down menu for filtering items by category.
    private JComboBox<String> cmCategory;
    // There is also a JTable called "table" which displays the items in the catalog in a table format
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Item> items;

    //this was made to test the images using updateTable 
    ImageIcon tShirt = new ImageIcon("img/shirt.png");
    ImageIcon hoodie = new ImageIcon("img/Hoodie.png");
    ImageIcon jeans = new ImageIcon("img/Jeans.png");
    ImageIcon shorts = new ImageIcon("img/Shorts.png");
    ImageIcon beanie = new ImageIcon("img/Beanie.png");
    ImageIcon hat = new ImageIcon("img/Hat.png");

    
    public Catalog(JFrame parent) throws SQLException, ClassNotFoundException {
        super(parent);
        setTitle("Catalog");
        catalogPanel = new JPanel(new BorderLayout());
        setContentPane(catalogPanel);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);

        items = new ArrayList<>();

        //this takes the catalog table from the datbase
        ArrayList result = query("catalog", "");
        for (int i = 0; i < result.size(); i++) {
            String[] splited = result.get(i).toString().split(" ");
            items.add(new Item(splited[0], splited[1], splited[2], Integer.parseInt(splited[3]), Double.parseDouble(splited[4]), null));
        }

        //sets up the JTable with the appropriate column headers and the JScrollPane for scrolling through the table.
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Size");
        model.addColumn("Price");
        model.addColumn("Image");
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        catalogPanel.add(scrollPane, BorderLayout.CENTER);
        table.setDefaultEditor(Object.class, null);

        table.setRowHeight(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        Dimension tableSize = new Dimension(600, 600);
        table.setPreferredScrollableViewportSize(tableSize);
        scrollPane.setPreferredSize(tableSize);

        JPanel filter = new JPanel();

        //this is creation of the search button to be able to search items using the preformSearch() method
        JTextField searchField = new JTextField(10);
        filter.add(searchField);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                performSearch(search);
            }
        });
        filter.add(search);

        //set up the filtering JComboBox
        cmCategory = new JComboBox<String>();
        cmCategory.addItem("All");
        cmCategory.addItem("Tops");
        cmCategory.addItem("Bottoms");
        cmCategory.addItem("Footwear");
        cmCategory.addItem("Hats");
        cmCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
        filter.add(cmCategory);
        catalogPanel.add(filter, BorderLayout.NORTH);
        cmCategory.setSelectedItem("All");

        // Back button
        JPanel button = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login myLogin = new Login(null);
            }
        });
        button.add(back);

        // Checkout button
        JButton checkout = new JButton("CheckOut");
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    OrderSummary mySummary = new OrderSummary(null, /*Cart.getItems(), */Catalog.this);
                } catch (Exception er) {
                    throw er;
                }
            }
        });
        button.add(checkout);

        // My WishList button
        JButton wishlist = new JButton("My WishList");
        wishlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WishlistPage myWishList = new WishlistPage(null, WishList.getItems(), Catalog.this);
            }
        });
        button.add(wishlist);

        // Return button
        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Return myReturn = new Return(null, Cart.getItems());
            }
        });
        button.add(returnBtn);

        //Order History
        JButton historyBtn = new JButton("Order History");
        historyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                History myHistory = new History(null, Catalog.this, new ArrayList<>());
                // should open the Order history page
            }
        });
        button.add(historyBtn);

        catalogPanel.add(button, BorderLayout.SOUTH);

        JPanel buttonOne = new JPanel();
        buttonOne.setLayout(new BoxLayout(buttonOne, BoxLayout.Y_AXIS));

        // Add to Cart button
        JButton addToCart = new JButton("Add To Cart");
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Item item = items.get(row);
                    Cart.addItem(item);
                    JOptionPane.showMessageDialog(catalogPanel, item.getName() + " added to cart.");
                } else {
                    JOptionPane.showMessageDialog(catalogPanel, "Please select an item that is available.");
                }
            }
        });
        buttonOne.add(addToCart);

        // Add To WishList button
        JButton addToWishlist = new JButton("Add To WishList");
        addToWishlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Item item = items.get(row);
                    WishList.addItem(item);
                    JOptionPane.showMessageDialog(catalogPanel, item.getName() + " added to wishlist.");
                } else {
                    JOptionPane.showMessageDialog(catalogPanel, "Please select an item that is available.");
                }
            }
        });
        buttonOne.add(addToWishlist);
        catalogPanel.add(buttonOne, BorderLayout.EAST);

        // Get notified button
        JButton getNotified = new JButton("Get Notified");
        getNotified.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    // Add get notofication logic
                    dispose();
                    GetNotifications getNotifications = new GetNotifications(null);
                } else {
                    JOptionPane.showMessageDialog(catalogPanel, "Please select an item to get notified about.");
                }
            }
        });
        buttonOne.add(getNotified);
        catalogPanel.add(buttonOne, BorderLayout.EAST);

        // Review button
        JButton reviewButton = new JButton("Product Reviews");
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Item item = items.get(row);
                    // add review logic
                    dispose();
                    Reviews review = new Reviews(null);
                } else {
                    JOptionPane.showMessageDialog(catalogPanel, "Please select an item for the reviews.");
                }
            }
        });
        buttonOne.add(reviewButton);


        setVisible(true);
        updateTable();

    }

    //The updateTable() method updates the JTable based on the currently selected category in the "cmCategory" JComboBox.
    private void updateTable() {
        model.setRowCount(0);
        String selectedCategory = (String) cmCategory.getSelectedItem();
        for (Item item : items)
            if (selectedCategory.equals("All") || selectedCategory.equals(item.getCategory())) {
                model.addRow(new Object[]{item.getName(), item.getSize(), "$" + String.format("%.2f", item.getPrice()), item.getImage()});
            }

        //this dispalys the images in the table, doesnt work with databse 
        table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                if (value != null) {
                    ImageIcon imageIcon = (ImageIcon) value;
                    Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(image));
                }

                return label;
            }
        });
    }

    // The performSearch() method performs a search for items in the JTable based on the input search string.
    private void performSearch(String search) {
        model.setRowCount(0);
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(search.toLowerCase())) {
                model.addRow(new Object[]{item.getName(), item.getSize(), "$" + String.format("%.2f", item.getPrice()), item.getImage()});
            }
        }
    }


}


