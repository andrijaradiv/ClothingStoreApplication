package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static clothing4you.JDBC.query;


public class Catalog extends JDialog {
    private JPanel catalogPanel;
    private JComboBox<String> cmCategory;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Item> items;
    private Cart cart;
    private WishList wl;

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


        cart = new Cart();
        wl = new WishList();
        items = new ArrayList<>();

        ArrayList result = query("catalog", "");
        for (int i = 0; i < result.size(); i++) {
            String[] splited = result.get(i).toString().split(" ");
            items.add(new Item(splited[0], splited[1], splited[2], Integer.parseInt(splited[3]), Double.parseDouble(splited[4]), null));
        }

//        items.add(new Item("T-shirt", "Tops", "M", 1, 20.00, tShirt));
//        items.add(new Item("Hoodie", "Tops", "M",1,25.00, hoodie));
//        items.add(new Item("Jeans", "Bottoms", "M",1, 20.00, jeans));
//        items.add(new Item("Shorts", "Bottoms", "M",1,15.00, shorts));
//        items.add(new Item("Beanie", "Hats", "M",1,7.50, beanie));
//        items.add(new Item("Hat", "Hats", "M",1,7.50, hat));



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
                    OrderSummary mySummary = new OrderSummary(null, cart.getItems(), Catalog.this);
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
                WishlistPage myWishList = new WishlistPage(null, wl.getItems(), Catalog.this);
            }
        });
        button.add(wishlist);

        // Return button
        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Return myReturn = new Return(null,cart.getItems());
            }
        });
        button.add(returnBtn);

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
                    cart.addItem(item);
                    JOptionPane.showMessageDialog(catalogPanel, item.getName() + " added to cart.");
                } else {
                    JOptionPane.showMessageDialog(catalogPanel, "Please select an item that is available.");
                }
            }
        });
        buttonOne.add(addToCart);

        // Add To WishList
        JButton addToWishlist = new JButton("Add To WishList");
        //wishlist.setPreferredSize(new Dimension(120,30));
        addToWishlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Item item = items.get(row);
                    wl.addItem(item);
                    JOptionPane.showMessageDialog(catalogPanel, item.getName() + " added to wishlist.");
                } else {
                    JOptionPane.showMessageDialog(catalogPanel, "Please select an item that is available.");
                }
            }
        });
        buttonOne.add(addToWishlist);
        catalogPanel.add(buttonOne, BorderLayout.EAST);


        setVisible(true);
        updateTable();

    }

    private void updateTable() {
        model.setRowCount(0);
        String selectedCategory = (String) cmCategory.getSelectedItem();
        for (Item item : items) {
            if (selectedCategory.equals("All") || selectedCategory.equals(item.getCategory())) {
                model.addRow(new Object[] { item.getName(), item.getSize(), "$" + String.format("%.2f",item.getPrice()), item.getImage() });
            }
        }

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

    private void performSearch(String search) {
        model.setRowCount(0);
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(search.toLowerCase())) {
                model.addRow(new Object[] { item.getName(), item.getSize(), "$" + String.format("%.2f",item.getPrice()), item.getImage() });
            }
        }
    }


}


