package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dao.custom.*;
import lk.ijse.pos.dao.custom.impl.OrderDetailDAOImpl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            if (SQLUtil.executeUpdate("insert into `order` values(?,?,?,?,?)", dto.getOderId(), dto.getCustomerId(), dto.getOrderDate(),
                    dto.getOrderTime(), dto.getCost())) {
                OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

                if (orderDetailDAO.saveOrderDetails(dto.getOderId(), dto.getItems())) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }

            } else {
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {

                connection.setAutoCommit(true);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    @Override
    public ItemDTO setItemDataForTextFields(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.search(itemId);

    }

    @Override
    public CustomerDTO setCustomerDataForTextFields(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.search(cusId);
    }

    @Override
    public ArrayList<String> loadCustomerIdsForCombo() throws SQLException, ClassNotFoundException {
        return customerDAO.loadIds();
    }

    @Override
    public ArrayList<String> loadItemIdsForCombo() throws SQLException, ClassNotFoundException {
        return itemDAO.loadIds();

    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.save(orderDTO);

    }

}
