package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl {
    private final ItemDAO itemDAO = new ItemDAOImpl();


    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
       return itemDAO.loadAll();
    }
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(dto);
    }
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(dto);
    }
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }
}

