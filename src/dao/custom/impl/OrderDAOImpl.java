package dao.custom.impl;

import bo.custom.impl.OrderBOImpl;
import dao.SQLUtil;
import dao.custom.OrderDAO;
import model.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean save(OrderDTO dto)  {
      /*  Connection connection=DbConnection.getInstance().getConnection();
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
*/

        OrderBOImpl orderBOImpl =new OrderBOImpl();
        try {
          return  orderBOImpl.purchaseOrder(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
          return false;
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDTO> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    //==============================================End CrudDAO============================================

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.executeQuery("select oId from `order` order by oId desc limit 1");

        if (resultSet.next()){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);

            tempId=tempId+1;

            if (tempId<10){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else {
                return "O-"+tempId;
            }

        }else {
            return "O-001";
        }
    }

}
