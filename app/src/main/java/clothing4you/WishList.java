package clothing4you;

import java.util.ArrayList;

public class WishList {
    private final ArrayList<Item> wishlistItems;

    public WishList() {
        wishlistItems = new ArrayList<>();
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



    public void addToCart(int index) {
        Item item = wishlistItems.get(index);
        Cart.addItem(item);
        wishlistItems.remove(index);
    }
}




