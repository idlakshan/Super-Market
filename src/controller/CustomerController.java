package controller;

import db.DbConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {

    public static boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("insert into customer values (?,?,?,?)");
        prepareStatement.setObject(1, customer.getId());
        prepareStatement.setObject(2, customer.getName());
        prepareStatement.setObject(3, customer.getAddress());
        prepareStatement.setObject(4, customer.getTp());

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }

    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("update customer set name=?,address=?,tp=? where id=?");
        prepareStatement.setObject(1, customer.getName());
        prepareStatement.setObject(2, customer.getAddress());
        prepareStatement.setObject(3, customer.getTp());
        prepareStatement.setObject(4, customer.getId());

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }

    public static Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from customer where id=?");
        prepareStatement.setObject(1, id);

        ResultSet resultSet = prepareStatement.executeQuery();

        Customer customer = new Customer();

        if (resultSet.next()) {
            customer.setId(resultSet.getString(1));
            customer.setName(resultSet.getString(2));
            customer.setAddress(resultSet.getString(3));
            customer.setTp(resultSet.getString(4));

            //System.out.println(resultSet);

            return customer;
        }


        return null;
    }

    public static boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("delete from customer where id=?");
        prepareStatement.setObject(1, id);

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }
    public static ArrayList<Customer> loadAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from customer");
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<Customer> customers=new ArrayList<>();

        while (resultSet.next()){
            Customer customer=new Customer();

            customer.setId(resultSet.getString(1));
            customer.setName(resultSet.getString(2));
            customer.setAddress(resultSet.getString(3));
            customer.setTp(resultSet.getString(4));

            customers.add(customer);

        }


        return customers;
    }


}
