package dao;

import db.DbConnection;
import model.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("insert into item values (?,?,?,?)");
        prepareStatement.setObject(1, itemDTO.getCode());
        prepareStatement.setObject(2, itemDTO.getName());
        prepareStatement.setObject(3, itemDTO.getQtyOnHand());
        prepareStatement.setObject(4, itemDTO.getPrice());

        int i = prepareStatement.executeUpdate();

        return i > 0;
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from item where code=?");
        prepareStatement.setObject(1, id);

        ResultSet resultSet = prepareStatement.executeQuery();

        // ItemDTO item = new ItemDTO();

        if (resultSet.next()) {
            /*item.setCode(resultSet.getString(1));
            item.setName(resultSet.getString(2));
            item.setQtyOnHand(Integer.parseInt(resultSet.getString(3)));
            item.setPrice(Double.parseDouble(resultSet.getString(4)));*/

            String code = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qty = Integer.parseInt(resultSet.getString(3));
            double price = Double.parseDouble(resultSet.getString(4));

            return new ItemDTO(code, name, qty, price);
        }

        return null;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("update item set name=?,qtyOnHand=?,price=? where code=?");
        prepareStatement.setObject(1, itemDTO.getName());
        prepareStatement.setObject(2, itemDTO.getQtyOnHand());
        prepareStatement.setObject(3, itemDTO.getPrice());
        prepareStatement.setObject(4, itemDTO.getCode());

        int i = prepareStatement.executeUpdate();

        return i > 0;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("delete from item where code=?");
        prepareStatement.setObject(1, id);

        int i = prepareStatement.executeUpdate();

        return i > 0;
    }

    @Override
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from item");
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        while (resultSet.next()) {
            //  ItemDTO item=new ItemDTO();

          /*  item.setCode(resultSet.getString(1));
            item.setName(resultSet.getString(2));
            item.setQtyOnHand(Integer.parseInt(resultSet.getString(3)));
            item.setPrice(Double.parseDouble(resultSet.getString(4)));
*/
            String code = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qty = Integer.parseInt(resultSet.getString(3));
            double price = Double.parseDouble(resultSet.getString(4));


            itemDTOS.add(new ItemDTO(code, name, qty, price));

        }


        return itemDTOS;
    }

    @Override
    public ArrayList<String> loadItemIds() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from item");
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<String> itemIds = new ArrayList<>();
        while (resultSet.next()) {
            itemIds.add(resultSet.getString(1));

        }
        return itemIds;
    }
}
