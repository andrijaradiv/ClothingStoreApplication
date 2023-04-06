package clothing4you;

import java.util.ArrayList;

// This is a class called Cart, which represents a shopping cart for a store.
public class Cart {
    // It contains a static ArrayList of Item objects called "items", which represents the items currently in the cart.
    private static final ArrayList<Item> items = new ArrayList<>(); 
    private static final double tax = 0.13;
    private static double totalPrice;

    private Cart(){

    }

    // The addItem() method adds an item to the "items" ArrayList and updates the "totalPrice" variable accordingly.
    public static void addItem(Item item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    // The removeItem() method removes an item from the "items" ArrayList and updates the "totalPrice" variable accordingly.
    public static void removeItem(Item item) {
        items.remove(item);
        totalPrice -= item.getPrice() * item.getQuantity();
    }

    // The getItems() method returns the "items" ArrayList.
    public static ArrayList<Item> getItems() {
        return items;
    }

    // The getTax() method calculates and returns the tax amount for the items in the cart by multiplying the "totalPrice" variable by the "tax" rate.
    public static double getTax() {
        totalPrice = getSubTotal();
        return totalPrice * tax;
    }

    // The getTotal() method calculates and returns the total amount for the items in the cart, including tax
    public static double getTotal() {
        totalPrice = 0.0;
        totalPrice = getSubTotal();
        return totalPrice + (totalPrice * tax);
    }

    // The getSubTotal() method calculates and returns the subtotal amount for the items in the cart, without tax
    public static double getSubTotal() {
        totalPrice = 0.0;
        for(Item item: items){
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    // The getQuantity() method calculates and returns the total quantity of items in the cart 
    public static int getQuantity(){
        int quantity = 0;
        for (Item item: items){
            quantity += item.getQuantity();
        }
        return quantity;
    }


}

