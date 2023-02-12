package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dto.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomDTO> searchOrderByOrderId(String id) throws ClassNotFoundException, SQLException {
        String sql="select `order`.oId,`order`.cId,`order`.date,`order`.time,`order`.cost,`order detail`.itemCode,`order detail`.qty,`order detail`.price from `order` inner join `order detail` on `order`.oId=`order detail`.orderId where `order`.oId=?;";
        ResultSet resultSet = SQLUtil.executeQuery(sql, id);
        ArrayList<CustomDTO> orderRecords=new ArrayList();

        while (resultSet.next()){
            orderRecords.add(new CustomDTO( resultSet.getString(1),resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getDouble(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getDouble(8)));
        }
        return orderRecords;
    }

}
