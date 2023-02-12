package bo.custom.impl;

import bo.custom.OrderBO;
import dao.CrudDAO;
import dao.DAOFactory;
import dao.SQLUtil;
import dao.SuperDAO;
import dao.custom.*;
import dao.custom.impl.*;
import db.DbConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {

       // private final ItemDAO itemDAO = new ItemDAOImpl();
      //  private final CustomerDAO customerDAO = new CustomerDAOImpl();
        // private final OrderDAO crudDAO = new OrderDAOImpl();
       // private final QueryDAO queryDAO = new QueryDAOImpl();



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
