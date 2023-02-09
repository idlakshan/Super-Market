package dao.custom;

import dao.CrudDAO;
import model.OrderDTO;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDTO,String> {
    public String getOrderId()throws SQLException, ClassNotFoundException;

    public boolean updateQty(String itemCode,int qty)throws SQLException, ClassNotFoundException;

    public boolean placeOrder(OrderDTO orderDTO)throws SQLException, ClassNotFoundException;

}
