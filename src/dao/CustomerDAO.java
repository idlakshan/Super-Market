package dao;

import db.DbConnection;
import model.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException;

    public ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException;
}
