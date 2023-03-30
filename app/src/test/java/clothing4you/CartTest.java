package clothing4you;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    @Test
    public void testAddItem() {
        Item item1 = new Item("Shirt", "Men's Clothing", "L", 1, 24.99, null);
        Item item2 = new Item("Pants", "Men's Clothing", "M", 1, 39.99, null);
        Cart.addItem(item1);
        Cart.addItem(item2);
        ArrayList<Item> items = Cart.getItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
        Cart.clearCart();
    }

    @Test
    public void testGetSubTotal() {
        Item item1 = new Item("Shirt", "Men's Clothing", "L", 1, 24.99, null);
        Item item2 = new Item("Pants", "Men's Clothing", "M", 1, 39.99, null);
        Cart.addItem(item1);
        Cart.addItem(item2);
        double subtotal = Cart.getSubTotal();
        assertEquals(64.98, subtotal);
        Cart.clearCart();
    }

    @Test
    public void testGetTax() {
        Item item1 = new Item("Shirt", "Men's Clothing", "L", 1, 24.99, null);
        Item item2 = new Item("Pants", "Men's Clothing", "M", 1, 39.99, null);
        Cart.addItem(item1);
        Cart.addItem(item2);
        double tax = Cart.getTax();
        assertEquals(8.4474, tax);
        Cart.clearCart();
    }

    @Test
    public void testGetTotal() {
        Item item1 = new Item("Shirt", "Men's Clothing", "L", 1, 24.99, null);
        Item item2 = new Item("Pants", "Men's Clothing", "M", 1, 39.99, null);
        Cart.addItem(item1);
        Cart.addItem(item2);
        double total = Cart.getTotal();
        assertEquals(73.4274, total);
        Cart.clearCart();
    }

    @Test
    public void testGetQuantity() {
        Item item1 = new Item("Shirt", "Men's Clothing", "L", 2, 24.99, null);
        Item item2 = new Item("Pants", "Men's Clothing", "M", 1, 39.99, null);
        Cart.addItem(item1);
        Cart.addItem(item2);
        int quantity = Cart.getQuantity();
        assertEquals(3, quantity);
        Cart.clearCart();
    }
}
