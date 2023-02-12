package entity;

public class order {
    private String oId;
    private String cId;
    private String date;
    private String time;
    private double cost;

    public order() {
    }

    public order(String oId, String cId, String date, String time, double cost) {
        this.oId = oId;
        this.cId = cId;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
