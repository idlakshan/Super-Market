package dao.custom.impl;

import dao.CrudDAO;
import dao.SQLUtil;
import dao.custom.OrderDetailDAO;
import db.DbConnection;
import model.CustomerDTO;
import model.OrderDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean update(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetailsDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetailsDTO> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        return null;
    }

   // ===================================================================================

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("update item set qtyOnHand=(qtyOnHand-" + qty + ")where code ='" + itemCode + "'");
    }


    @Override
    public boolean saveOrderDetails(String orderId, ArrayList<OrderDetailsDTO> items) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO orderDetailsDTO : items
        ) {
            PreparedStatement statement = DbConnection.getInstance().
                    getConnection().prepareStatement("insert into `order detail` values(?,?,?,?)");
            statement.setObject(1, orderDetailsDTO.getItemCode());
            statement.setObject(2, orderId);
            statement.setObject(3, orderDetailsDTO.getQtyForSell());
            statement.setObject(4, orderDetailsDTO.getPrice());

            if (statement.executeUpdate() > 0) {
                if (updateQty(orderDetailsDTO.getItemCode(), orderDetailsDTO.getQtyForSell())) {

                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
        return true;
    }
}
