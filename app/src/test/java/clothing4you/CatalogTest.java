package clothing4you;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogTest {
    private Catalog catalog;

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        catalog = new Catalog(null);
    }

    @Test
    public void testUpdateTable() {
        // Select category
        catalog.cmCategory.setSelectedItem("Hats");

        // Check that only hats are displayed
        DefaultTableModel model = (DefaultTableModel) catalog.table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String category = (String) model.getValueAt(i, 1);
            assertEquals("Hats", category);
        }
    }

    @Test
    public void testPerformSearch() {
        // Perform search for "Hoodie"
        catalog.performSearch("Hoodie");

        // Check that only "Hoodie" is displayed
        DefaultTableModel model = (DefaultTableModel) catalog.table.getModel();
        assertEquals(1, model.getRowCount());
        String name = (String) model.getValueAt(0, 0);
        assertEquals("Hoodie", name);
    }

    @Test
    public void testAddToCart() {
        // Select "Hoodie" and click "Add to Cart" button
        catalog.table.setRowSelectionInterval(1, 1);
        JButton addToCartButton = (JButton) ((JPanel) catalog.getContentPane().getComponent(1)).getComponent(0);
        addToCartButton.doClick();

        // Check that "Hoodie" is in the cart
        ArrayList<Item> items = Cart.getItems();
        boolean found = false;
        for (Item item : items) {
            if (item.getName().equals("Hoodie")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testAddToWishlist() {
        // Select "Hoodie" and click "Add to Wishlist" button
        catalog.table.setRowSelectionInterval(1, 1);
        JButton addToWishlistButton = (JButton) ((JPanel) catalog.getContentPane().getComponent(2)).getComponent(1);
        addToWishlistButton.doClick();

        // Check that "Hoodie" is in the wishlist
        ArrayList<Item> items = catalog.wl.getItems();
        boolean found = false;
        for (Item item : items) {
            if (item.getName().equals("Hoodie")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

 }
