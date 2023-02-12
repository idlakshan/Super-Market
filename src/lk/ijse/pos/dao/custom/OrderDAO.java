package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dto.OrderDTO;


import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDTO,String> {
    public String getOrderId()throws SQLException, ClassNotFoundException;


}
