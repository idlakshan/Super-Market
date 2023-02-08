package model;

public class ItemDetails {
    private String itemCode;
    private int qtyForSell;
    private double price;

    public ItemDetails() {
    }

    public ItemDetails(String itemCode, int qtyForSell, double price) {
        this.itemCode = itemCode;
        this.qtyForSell = qtyForSell;
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", qty=" + qtyForSell +
                ", price=" + price +
                '}';
    }
}
