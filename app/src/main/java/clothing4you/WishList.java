package clothing4you;

import java.util.ArrayList;

public class WishList {
    private final ArrayList<Item> wishlistItems;
    private final Cart cart;

    public WishList() {
        wishlistItems = new ArrayList<>();
        cart = null;
    }

    public void addItem(Item item) {
        wishlistItems.add(item);
    }

    public void removeItem(int index){
        wishlistItems.remove(index);
    }

    public ArrayList<Item> getItems() {
        return wishlistItems;
    }

    public Cart getCart() {
        return cart;
    }

    public void addToCart(int index) {
        Item item = wishlistItems.get(index);
        cart.addItem(item);
        wishlistItems.remove(index);
    }
}




