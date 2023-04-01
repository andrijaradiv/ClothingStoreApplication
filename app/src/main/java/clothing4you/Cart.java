package clothing4you;

import java.util.ArrayList;

public class Cart {
    private static final ArrayList<Item> items = new ArrayList<>();
    private static final double tax = 0.13;
    private static double totalPrice;

    private Cart(){

    }

    public static void addItem(Item item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    public static void removeItem(Item item) {
        items.remove(item);
        totalPrice -= item.getPrice() * item.getQuantity();
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static double getTax() {
        totalPrice = getSubTotal();
        return totalPrice * tax;
    }

    public static double getTotal() {
        totalPrice = 0.0;
        totalPrice = getSubTotal();
        return totalPrice + (totalPrice * tax);
    }

    public static double getSubTotal() {
        totalPrice = 0.0;
        for(Item item: items){
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public static int getQuantity(){
        int quantity = 0;
        for (Item item: items){
            quantity += item.getQuantity();
        }
        return quantity;
    }


}

