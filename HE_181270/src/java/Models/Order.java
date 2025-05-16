package Models;

public class Order {

    private String orderID;
    private double totalPrice;
    private String createDate;
    private int accountID;
    private String status;

    public Order() {
    }

    public Order(String orderID, double totalPrice, String createDate, int accountID, String status) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.accountID = accountID;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", totalPrice=" + totalPrice + ", createDate=" + createDate + ", accountID=" + accountID + '}';
    }

}
