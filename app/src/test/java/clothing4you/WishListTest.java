package clothing4you;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WishListTest {

    private Item item1;
    private Item item2;
    private Item item3;

    @BeforeEach
    void setUp() {
        item1 = new Item("T-shirt", "shirt", "A stylish T-shirt", 1, 100, null);
        item2 = new Item("Jeans", "shirt", "A comfortable pair of jeans", 1, 100, null);
        item3 = new Item("Socks", "shirt", "A cozy pair of socks", 1, 100, null);
    }

    @Test
    void addItem() {
        WishList.addItem(item1);
        WishList.addItem(item2);

        ArrayList<Item> items = WishList.getItems();
        assertTrue(items.contains(item1), "WishList should contain the added item1");
        assertTrue(items.contains(item2), "WishList should contain the added item2");
    }

    @Test
    void removeItem() {
        WishList.addItem(item1);
        WishList.addItem(item2);
        WishList.removeItem(item1);

        ArrayList<Item> items = WishList.getItems();
        assertTrue(!items.contains(item1), "WishList should not contain the removed item1");
        assertTrue(items.contains(item2), "WishList should contain the non-removed item2");
    }

    @Test
    void addToCart() {
        WishList.addItem(item1);
        WishList.addItem(item2);
        WishList.addToCart(0); // Add item1 to the cart

        ArrayList<Item> items = WishList.getItems();
        assertTrue(items.contains(item2), "WishList should contain the non-removed item2");

    }
}
