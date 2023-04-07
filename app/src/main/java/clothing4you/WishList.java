package clothing4you;

import java.util.ArrayList;
//this is an object that would store your wishlist items 
public class WishList {

    private static final ArrayList<Item> wishlistItems = new ArrayList<>();

    private WishList() {

    }
    //getters and setters for the wishlist object 
    public static void addItem(Item item) {
        wishlistItems.add(item);
    }

    public static void removeItem(Item item){
        wishlistItems.remove(item);
    }

    public static ArrayList<Item> getItems() {
        return wishlistItems;
    }

    public static void addToCart(int index) {
        Item item = wishlistItems.get(index);
        Cart.addItem(item);
        wishlistItems.remove(index);
    }
}




