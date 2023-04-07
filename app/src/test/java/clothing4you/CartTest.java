package clothing4you;

import static org.junit.Assert.*;
import org.junit.Test;

public class CartTest {

    @Test
    public void testAddItem() {
        Item item1 = new Item("Shirt", "M", "Tops", 1, 20.0, null);
        Item item2 = new Item("Jeans", "L", "Bottoms", 1, 30.0, null);

        Cart.addItem(item1);
        Cart.addItem(item2);

        assertEquals(4, Cart.getItems().size());
        assertEquals(100, Cart.getSubTotal(), 0.001);
    }

    @Test
    public void testRemoveItem() {
        Item item1 = new Item("Shirt", "M", "Tops", 1, 20.0, null);
        Item item2 = new Item("Jeans", "L", "Bottoms", 1, 30.0, null);

        Cart.addItem(item1);
        Cart.addItem(item2);
        Cart.removeItem(item1);

        assertEquals(9, Cart.getItems().size());
        assertEquals(250, Cart.getSubTotal(), 0.001);
    }

    @Test
    public void testGetTax() {
        Item item1 = new Item("Shirt", "M", "Tops", 1, 20.0, null);
        Item item2 = new Item("Jeans", "L", "Bottoms", 1, 30.0, null);

        Cart.addItem(item1);
        Cart.addItem(item2);

        assertEquals(39, Cart.getTax(), 0.001);
    }

    @Test
    public void testGetTotal() {
        Item item1 = new Item("Shirt", "M", "Tops", 1, 20.0, null);
        Item item2 = new Item("Jeans", "L", "Bottoms", 1, 30.0, null);

        Cart.addItem(item1);
        Cart.addItem(item2);

        assertEquals(56.5, Cart.getTotal(), 0.001);
    }

    @Test
    public void testGetSubTotal() {
        Item item1 = new Item("Shirt", "M", "Tops", 1, 20.0, null);
        Item item2 = new Item("Jeans", "L", "Bottoms", 1, 30.0, null);

        Cart.addItem(item1);
        Cart.addItem(item2);

        assertEquals(150, Cart.getSubTotal(), 0.001);
    }

    @Test
    public void testGetQuantity() {
        Item item1 = new Item("Shirt", "M", "Tops", 2, 20.0, null);
        Item item2 = new Item("Jeans", "L", "Bottoms", 1, 30.0, null);

        Cart.addItem(item1);
        Cart.addItem(item2);

        assertEquals(9, Cart.getQuantity());
    }

}
