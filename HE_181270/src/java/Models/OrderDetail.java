package Models;

public class OrderDetail {

    private String orderDetailID;
    private int quantity;
    private int orderID;
    private int tourID;

    public OrderDetail() {
    }

    public OrderDetail(String orderDetailID, int quantity, int orderID, int tourID) {
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
        this.orderID = orderID;
        this.tourID = tourID;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailID=" + orderDetailID + ", quantity=" + quantity + ", orderID=" + orderID + ", tourID=" + tourID + '}';
    }

}
