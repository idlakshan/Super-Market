package dao.custom;

import dao.CrudDAO;
import model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetailsDTO,String> {

    public boolean saveOrderDetails(String orderId, ArrayList<OrderDetailsDTO> items)throws SQLException, ClassNotFoundException;
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
}
