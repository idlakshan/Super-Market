package bo.custom;

import bo.SuperBO;
import dao.SuperDAO;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

}
