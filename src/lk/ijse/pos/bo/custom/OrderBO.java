package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {

    boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    ItemDTO setItemDataForTextFields(String itemId) throws SQLException, ClassNotFoundException;

    CustomerDTO setCustomerDataForTextFields(String cusId) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadCustomerIdsForCombo() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadItemIdsForCombo() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

}
