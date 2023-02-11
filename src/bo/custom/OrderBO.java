package bo.custom;

import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO {

    boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    ItemDTO setItemDataForTextFields(String itemId) throws SQLException, ClassNotFoundException;

    CustomerDTO setCustomerDataForTextFields(String cusId) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadCustomerIdsForCombo() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadItemIdsForCombo() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

}
