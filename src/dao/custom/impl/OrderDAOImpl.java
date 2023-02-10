package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.OrderDAO;
import db.DbConnection;
import model.OrderDetailsDTO;
import model.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        boolean b = SQLUtil.executeUpdate("update item set qtyOnHand=(qtyOnHand-" + qty + ")where code ='" + itemCode + "'");
        return b;
    }

    @Override
    public boolean placeOrder(OrderDTO orderDTO) {
        Connection connection=null;
        try {
             connection=DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("insert into `order` values(?,?,?,?,?)");
            statement.setObject(1, orderDTO.getOderId());
            statement.setObject(2, orderDTO.getCustomerId());
            statement.setObject(3, orderDTO.getOrderDate());
            statement.setObject(4, orderDTO.getOrderTime());
            statement.setObject(5, orderDTO.getCost());


            if (statement.executeUpdate() > 0) {
                if (saveOrderDetails(orderDTO.getOderId(), orderDTO.getItems())){
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

    private boolean saveOrderDetails(String orderId, ArrayList<OrderDetailsDTO> items) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO orderDetailsDTO : items
        ) {
            PreparedStatement statement = DbConnection.getInstance().
                    getConnection().prepareStatement("insert into `order detail` values(?,?,?,?)");
            statement.setObject(1, orderDetailsDTO.getItemCode());
            statement.setObject(2, orderId);
            statement.setObject(3, orderDetailsDTO.getQtyForSell());
            statement.setObject(4, orderDetailsDTO.getPrice());

            if (statement.executeUpdate() > 0) {
                if (updateQty(orderDetailsDTO.getItemCode(), orderDetailsDTO.getQtyForSell())) {

                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
        return true;

    }

}
