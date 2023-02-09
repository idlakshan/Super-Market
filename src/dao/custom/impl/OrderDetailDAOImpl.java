package dao.custom.impl;

import dao.CrudDAO;
import dao.custom.OrderDetailDAO;
import model.CustomerDTO;
import model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetailsDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetailsDTO> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        return null;
    }

}
