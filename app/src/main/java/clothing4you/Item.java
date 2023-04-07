package clothing4you;

import javax.swing.*;
import java.awt.*;

public class Item {
    // Private fields for the item's name, category, size, quantity, price, and image
    private String name;
    private String category;
    private String size;
    private int quantity;
    private double price;
    private ImageIcon image;

    // Constructor that takes the item's fields as arguments and initializes them
    public Item(String name, String category,String size, int quantity, double price, ImageIcon image) {
        this.name = name;
        this.category = category;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }


    // Getter and setter methods for the item's name, category, size, quantity, price, and image
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ImageIcon getImage() {
        return image;
    }

    // Method that returns the item's ID (currently hardcoded to always return 0)
    public int getId() {
        return 0;
    }
}
