package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.dto.OrderDetailsDTO;


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

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("update item set qtyOnHand=(qtyOnHand-" + qty + ")where code ='" + itemCode + "'");
    }

    @Override
    public boolean saveOrderDetails(String orderId, ArrayList<OrderDetailsDTO> items) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO orderDetailsDTO : items
        ) {
            if (SQLUtil.executeUpdate("insert into `order detail` values(?,?,?,?)",orderDetailsDTO.getItemCode(),orderId,
                    orderDetailsDTO.getQtyForSell(),orderDetailsDTO.getPrice())) {
                if (updateQty(orderDetailsDTO.getItemCode(), orderDetailsDTO.getQtyForSell())) {
                    // new Alert(Alert.AlertType.CONFIRMATION,"okk");
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
