package lk.ijse.pos.view.tm;

public class CartTm {
    private String code;
    private String cusId;
    private String name;
    private int qty;
    private double price;
    private double total;

    public CartTm() {
    }

    public CartTm(String code, String cusId, String name, int qty, double price, double total) {
        this.code = code;
        this.cusId = cusId;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
