package bo;

import dao.CrudDAO;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl {
    private final CustomerDAO customerDAO=new CustomerDAOImpl();


    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
             return customerDAO.loadAll();

    }


}
