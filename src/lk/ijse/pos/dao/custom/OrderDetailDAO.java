package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dto.OrderDetailsDTO;


import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetailsDTO,String> {

    public boolean saveOrderDetails(String orderId, ArrayList<OrderDetailsDTO> items)throws SQLException, ClassNotFoundException;
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
}
