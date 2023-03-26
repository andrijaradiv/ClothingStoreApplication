package clothing4you;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishlistPage extends JDialog {
    private JPanel wishlistPanel;
    private ArrayList<Item> items;
    private JTable table;
    private DefaultTableModel model;
    private WishList wishList;
    private Cart cart;
    private Catalog previousCatalog;
    private JComboBox<String> cmCategory; //

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
        cart = new Cart();
        wishList = new WishList();

        model = new DefaultTableModel(new Object[]{"Name", "Price", "Category"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        wishlistPanel.add(scrollPane, BorderLayout.CENTER);
        table.setDefaultEditor(Object.class, null);

        for (Item item: items){
            model.addRow(new Object[]{item.getName(), item.getPrice(), item.getCategory()});
        }

        JPanel button = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                previousCatalog.setVisible(true);
            }
        });
        button.add(back);

        JButton addToCart = new JButton("Add To Cart");
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Item item = items.get(row);
                    cart.addItem(item);
                    JOptionPane.showMessageDialog(wishlistPanel, item.getName() + " added to cart.");
                } else {
                    JOptionPane.showMessageDialog(wishlistPanel, "Please select an item that is available.");
                }
            }
        });
        button.add(addToCart);

        JButton checkout = new JButton("CheckOut");
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    OrderSummary mySummary = new OrderSummary(null, cart.getItems()); //need to add parameter "WishlistPage.this"!!
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        button.add(checkout);

        wishlistPanel.add(button, BorderLayout.SOUTH);


        setVisible(true);
        //updateTable();
    }

    // Overloaded constructor
    public WishlistPage(JFrame parent, ArrayList<Item> items) throws SQLException, ClassNotFoundException {
        // implementation
        this(parent, items, new Catalog(parent));
    }

}
