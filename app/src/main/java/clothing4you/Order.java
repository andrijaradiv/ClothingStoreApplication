package clothing4you;

public class Order {
    private String date;
    private String orderNumber;
    private String amount;

    public Order(String date, String orderNumber, String amount) {
        this.date = date;
        this.orderNumber = orderNumber;
        this.amount = amount;
    }

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

