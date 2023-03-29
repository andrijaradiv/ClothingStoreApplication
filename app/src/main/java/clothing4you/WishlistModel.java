package clothing4you;

import java.util.ArrayList;

//public class WishList {
//    private ArrayList<Item> items;
//
//    public WishList() {
//        items = new ArrayList<Item>();
//    }
//
//    public void addItem(Item item) {
//        items.add(item);
//    }
//
//    public ArrayList<Item> getItems() {
//        return items;
//    }
//}
// Old name of the class = WishList.java
public class WishlistModel {
    private ArrayList<Item> wishlistItems;
    private Cart cart;

    public WishlistModel() { // does this need parameter of items?
        this.wishlistItems = new ArrayList<Item>();
        this.cart = new Cart();   // use old cart - cart should be static so there is only one cart
    }

    public void addItemToWishlist(Item item) {
        wishlistItems.add(item);
    }

    public ArrayList<Item> getWishlistItems() {
        return wishlistItems;
    }

    public void addToCart(int index) {
        Item item = wishlistItems.get(index);
        cart.addItem(item);
        wishlistItems.remove(index);
    }

    public void removeFromWishlist(int index) {
        wishlistItems.remove(index);
    }

    public Cart getCart() {
        return cart;
    }
}
