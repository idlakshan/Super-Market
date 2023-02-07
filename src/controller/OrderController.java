package controller;

import db.DbConnection;
import model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    public boolean placeOrder(Order order){
        try {
            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("insert into `order` values(?,?,?,?,?)");
            statement.setObject(1,order.getOderId());
            statement.setObject(2,order.getCustomerId());
            statement.setObject(3,order.getOrderDate());
            statement.setObject(4,order.getOrderTime());
            statement.setObject(5,order.getCost());

            return statement.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
