package dao;

import db.DbConnection;
import model.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {

    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException;

    public ArrayList<String> loadItemIds() throws SQLException, ClassNotFoundException;
}
