package clothing4you;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WishListTest {

    @Test
    public void testAddItem() {
        WishList wishList = new WishList();
        Item item = new Item("T-shirt", "Tops", "M", 1, 20.00, null);
        wishList.addItem(item);

        ArrayList<Item> expectedItems = new ArrayList<>();
        expectedItems.add(item);

        assertEquals(expectedItems, wishList.getItems());
    }

    @Test
    public void testRemoveItem() {
        WishList wishList = new WishList();
        Item item1 = new Item("T-shirt", "Tops", "M", 1, 20.00, null);
        Item item2 = new Item("Jeans", "Bottoms", "M", 1, 30.00, null);
        wishList.addItem(item1);
        wishList.addItem(item2);

        wishList.removeItem(0);

        ArrayList<Item> expectedItems = new ArrayList<>();
        expectedItems.add(item2);

        assertEquals(expectedItems, wishList.getItems());
    }

    @Test
    public void testGetItems() {
        WishList wishList = new WishList();
        Item item1 = new Item("T-shirt", "Tops", "M", 1, 20.00, null);
        Item item2 = new Item("Jeans", "Bottoms", "M", 1, 30.00, null);
        wishList.addItem(item1);
        wishList.addItem(item2);

        ArrayList<Item> expectedItems = new ArrayList<>();
        expectedItems.add(item1);
        expectedItems.add(item2);

        assertEquals(expectedItems, wishList.getItems());
    }

    @Test
    public void testAddToCart() {
        WishList wishList = new WishList();
        Item item1 = new Item("T-shirt", "Tops", "M", 1, 20.00, null);
        Item item2 = new Item("Jeans", "Bottoms", "M", 1, 30.00, null);
        wishList.addItem(item1);
        wishList.addItem(item2);

        wishList.addToCart(0);

        ArrayList<Item> expectedItems = new ArrayList<>();
        expectedItems.add(item2);

        assertEquals(expectedItems, wishList.getItems());

        ArrayList<Item> expectedCartItems = new ArrayList<>();
        expectedCartItems.add(item1);

        assertEquals(expectedCartItems, Cart.getItems());
    }

}
