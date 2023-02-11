package bo;

import dao.CrudDAO;
import dao.SQLUtil;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDetailDAO;
import dao.custom.QueryDAO;
import dao.custom.impl.*;
import db.DbConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl {

    private final ItemDAO itemDAO = new ItemDAOImpl();
    private final CustomerDAO customerDAO=new CustomerDAOImpl();
    private final CrudDAO<CustomerDTO,String> customerDAOImpl=new CustomerDAOImpl();
    private final CrudDAO<ItemDTO,String> itemDAOImpl = new ItemDAOImpl();
    private final CrudDAO<OrderDTO,String> crudDAO = new OrderDAOImpl();
    private final QueryDAO queryDAO=new QueryDAOImpl();


    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            if ( SQLUtil.executeUpdate("insert into `order` values(?,?,?,?,?)",dto.getOderId(),dto.getCustomerId(),dto.getOrderDate(),
                    dto.getOrderTime(),dto.getCost())) {
                OrderDetailDAO orderDetailDAO=new OrderDetailDAOImpl();

                if ( orderDetailDAO.saveOrderDetails(dto.getOderId(), dto.getItems())){
                    connection.commit();
                    return true;
                }else {
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
        }
        finally {
            try {

                connection.setAutoCommit(true);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }
    public ItemDTO setItemDataForTextFields(String itemId) throws SQLException, ClassNotFoundException {
       return itemDAO.search(itemId);
       // return itemDTO;
    }
    public CustomerDTO setCustomerDataForTextFields(String cusId) throws SQLException, ClassNotFoundException {
     return customerDAO.search(cusId);

    }
      public ArrayList<String> loadCustomerIdsForCombo() throws SQLException, ClassNotFoundException {
         return customerDAOImpl.loadIds();

      }

    public ArrayList<String> loadItemIdsForCombo() throws SQLException, ClassNotFoundException {
      return itemDAOImpl.loadIds();

    }
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
      return crudDAO.save(orderDTO);

    }

}
