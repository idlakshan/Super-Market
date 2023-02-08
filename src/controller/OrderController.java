package controller;

import db.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().
                getConnection().prepareStatement("select oId from `order` order by oId desc limit 1 ");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);

            tempId=tempId+1;

            if (tempId<9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else {
                return "O-"+tempId;
            }

        }else {
            return "O-001";
        }
    }


    public boolean placeOrder(Order order) {
        Connection connection=null;
        try {
            connection=DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("insert into `order` values(?,?,?,?,?)");
            statement.setObject(1, order.getOderId());
            statement.setObject(2, order.getCustomerId());
            statement.setObject(3, order.getOrderDate());
            statement.setObject(4, order.getOrderTime());
            statement.setObject(5, order.getCost());


            if (statement.executeUpdate() > 0) {
                if (saveOrderDetails(order.getOderId(), order.getItems())){
                    connection.commit();
                        return true;
                }else {
                    connection.rollback();
                    return false;
                }

            } else {
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {

                connection.setAutoCommit(true);

            } catch (SQLException e) {
                e.printStackTrace();
            }
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
                if (updateQty(itemDetails.getItemCode(), itemDetails.getQtyForSell())) {

                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
        return true;

    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().
                getConnection().prepareStatement("update item set qtyOnHand=(qtyOnHand-" + qty + ")where code ='" + itemCode + "'");
        return statement.executeUpdate() > 0;

    }

}
