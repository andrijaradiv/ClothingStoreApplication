package clothing4you;
//this is the object that contains the info about x order (date it was bought, order number, and amount that was paid)
public class Order {
    private String date;
    private String orderNumber;
    private String amount;

    public Order(String date, String orderNumber, String amount) {
        this.date = date;
        this.orderNumber = orderNumber;
        this.amount = amount;
    }
    
    //all the getters and setters for the order object 
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

