package clothing4you;

import clothing4you.backend.Item;
import org.junit.jupiter.api.Test;
import javax.swing.ImageIcon;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void testGetName() {
        String name = "Shirt";
        Item item = new Item(name, "Clothing", "M", 1, 19.99, new ImageIcon());
        assertEquals(name, item.getName());
    }

    @Test
    public void testGetCategory() {
        String category = "Clothing";
        Item item = new Item("Shirt", category, "M", 1, 19.99, new ImageIcon());
        assertEquals(category, item.getCategory());
    }

    @Test
    public void testGetSize() {
        String size = "M";
        Item item = new Item("Shirt", "Clothing", size, 1, 19.99, new ImageIcon());
        assertEquals(size, item.getSize());
    }

    @Test
    public void testGetQuantity() {
        int quantity = 1;
        Item item = new Item("Shirt", "Clothing", "M", quantity, 19.99, new ImageIcon());
        assertEquals(quantity, item.getQuantity());
    }

    @Test
    public void testGetPrice() {
        double price = 19.99;
        Item item = new Item("Shirt", "Clothing", "M", 1, price, new ImageIcon());
        assertEquals(price, item.getPrice(), 0.001);
    }

    @Test
    public void testItemCreationWithNullImage() {
        String name = "Test Item";
        String category = "Test Category";
        String size = "M";
        int quantity = 10;
        double price = 9.99;
        ImageIcon image = null;

        Item item = new Item(name, category, size, quantity, price, image);

        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(category, item.getCategory());
        assertEquals(size, item.getSize());
        assertEquals(quantity, item.getQuantity());
        assertEquals(price, item.getPrice(), 0.01);
        assertNull(item.getImage());
    }



}
