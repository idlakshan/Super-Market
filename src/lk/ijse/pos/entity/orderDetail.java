package lk.ijse.pos.entity;

public class orderDetail {
    private String itemCode;
    private String orderId;
    private int qty;
    private double price;

    public orderDetail() {
    }

    public orderDetail(String itemCode, String orderId, int qty, double price) {
        this.itemCode = itemCode;
        this.orderId = orderId;
        this.qty = qty;
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
