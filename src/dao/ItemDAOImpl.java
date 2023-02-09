package dao;

import model.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("insert into item values (?,?,?,?)", itemDTO.getCode(), itemDTO.getName(), itemDTO.getQtyOnHand(), itemDTO.getPrice());
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from item where code=?", id);
        if (resultSet.next()) {
            return new ItemDTO(resultSet.getString(1), resultSet.getString(2), Integer.parseInt(resultSet.getString(3)), Double.parseDouble(resultSet.getString(4)));
        }
        return null;

    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("update item set name=?,qtyOnHand=?,price=? where code=?", itemDTO.getName(), itemDTO.getQtyOnHand(), itemDTO.getPrice(), itemDTO.getCode());
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("delete from item where code=?", id);
    }

    @Override
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from item");
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        while (resultSet.next()) {
            itemDTOS.add(new ItemDTO(resultSet.getString(1), resultSet.getString(2), Integer.parseInt(resultSet.getString(3)), Double.parseDouble(resultSet.getString(4))));
        }
        return itemDTOS;
    }

    @Override
    public ArrayList<String> loadItemIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from item");
        ArrayList<String> itemIds = new ArrayList<>();

        while (resultSet.next()) {
            itemIds.add(resultSet.getString(1));
        }
        return itemIds;
    }
}
