package controller;

import db.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    public boolean placeOrder(Order order) {
        try {
            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("insert into `order` values(?,?,?,?,?)");
            statement.setObject(1, order.getOderId());
            statement.setObject(2, order.getCustomerId());
            statement.setObject(3, order.getOrderDate());
            statement.setObject(4, order.getOrderTime());
            statement.setObject(5, order.getCost());


         if(statement.executeUpdate()>0){
          return (saveOrderDetails(order.getOderId(), order.getItems()));

         }else {
             return false;
         }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveOrderDetails(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        for (ItemDetails itemDetails : items
        ) {
            PreparedStatement statement = DbConnection.getInstance().
                    getConnection().prepareStatement("insert into `order detail` values(?,?,?,?)");
            statement.setObject(1, itemDetails.getItemCode());
            statement.setObject(2, orderId);
            statement.setObject(3, itemDetails.getQtyForSell());
            statement.setObject(4, itemDetails.getPrice());

            if (statement.executeUpdate() > 0) {
                    
            } else {
                return false;
            }

        }
        return true;

    }

}
