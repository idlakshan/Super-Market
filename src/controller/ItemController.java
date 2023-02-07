package controller;

import db.DbConnection;
import model.Customer;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemController {
    public static boolean saveItem(Item item) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("insert into item values (?,?,?,?)");
        prepareStatement.setObject(1, item.getCode());
        prepareStatement.setObject(2, item.getName());
        prepareStatement.setObject(3, item.getQtyOnHand());
        prepareStatement.setObject(4, item.getPrice());

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }
        return false;
    }

    public static Item searchItem(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from item where code=?");
        prepareStatement.setObject(1, id);

        ResultSet resultSet = prepareStatement.executeQuery();

        Item item = new Item();

        if (resultSet.next()) {
            item.setCode(resultSet.getString(1));
            item.setName(resultSet.getString(2));
            item.setQtyOnHand(Integer.parseInt(resultSet.getString(3)));
            item.setPrice(Double.parseDouble(resultSet.getString(4)));

            return item;
        }

        return null;
    }
    public static boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("update item set name=?,qtyOnHand=?,price=? where code=?");
        prepareStatement.setObject(1, item.getName());
        prepareStatement.setObject(2, item.getQtyOnHand());
        prepareStatement.setObject(3, item.getPrice());
        prepareStatement.setObject(4, item.getCode());

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }
    public static boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("delete from item where code=?");
        prepareStatement.setObject(1, id);

        int i = prepareStatement.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }
    public static ArrayList<Item> loadAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from item");
        ResultSet resultSet = prepareStatement.executeQuery();

        ArrayList<Item> items=new ArrayList<>();

        while (resultSet.next()){
           Item item=new Item();

            item.setCode(resultSet.getString(1));
            item.setName(resultSet.getString(2));
            item.setQtyOnHand(Integer.parseInt(resultSet.getString(3)));
            item.setPrice(Double.parseDouble(resultSet.getString(4)));

            items.add(item);

        }


        return items;
    }


}
