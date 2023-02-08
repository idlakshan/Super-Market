package dao;

import db.DbConnection;
import model.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("insert into customer values (?,?,?,?)");
        prepareStatement.setObject(1, customerDTO.getId());
        prepareStatement.setObject(2, customerDTO.getName());
        prepareStatement.setObject(3, customerDTO.getAddress());
        prepareStatement.setObject(4, customerDTO.getTp());

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("update customer set name=?,address=?,tp=? where id=?");
        prepareStatement.setObject(1, customerDTO.getName());
        prepareStatement.setObject(2, customerDTO.getAddress());
        prepareStatement.setObject(3, customerDTO.getTp());
        prepareStatement.setObject(4, customerDTO.getId());

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from customer where id=?");
        prepareStatement.setObject(1, id);

        ResultSet resultSet = prepareStatement.executeQuery();

        CustomerDTO customerDTO = new CustomerDTO();

        if (resultSet.next()) {
            customerDTO.setId(resultSet.getString(1));
            customerDTO.setName(resultSet.getString(2));
            customerDTO.setAddress(resultSet.getString(3));
            customerDTO.setTp(resultSet.getString(4));

            //System.out.println(resultSet);

            return customerDTO;
        }


        return null;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("delete from customer where id=?");
        prepareStatement.setObject(1, id);

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }

    @Override
    public  ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from customer");
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<CustomerDTO> customerDTOS =new ArrayList<>();

        while (resultSet.next()){
          /*  CustomerDTO customer=new CustomerDTO();

            customer.setId(resultSet.getString(1));
            customer.setName(resultSet.getString(2));
            customer.setAddress(resultSet.getString(3));
            customer.setTp(resultSet.getString(4));*/

            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tp = resultSet.getString(4);

            customerDTOS.add(new CustomerDTO(id,name,address,tp));

        }


        return customerDTOS;
    }

    @Override
    public ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from customer");
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<String> customerIds=new ArrayList<>();
        while (resultSet.next()){
            customerIds.add(resultSet.getString(1));

        }
        return customerIds;
    }


}
