package dao.custom;

import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO {
    ArrayList<CustomDTO> searchOrderByOrderId(String id) throws ClassNotFoundException, SQLException;
}
