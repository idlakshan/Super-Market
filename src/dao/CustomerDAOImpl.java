package dao;

import model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CrudDAO<CustomerDTO,String> {

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("insert into customer values (?,?,?,?)", customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTp());
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("update customer set name=?,address=?,tp=? where id=?", customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTp(), customerDTO.getId());
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from customer where id=?", id);
        if (resultSet.next()) {
            return new CustomerDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }
        return null;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("delete from customer where id=?", id);
    }

    @Override
    public ArrayList<CustomerDTO> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from customer");
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        while (resultSet.next()) {
            customerDTOS.add(new CustomerDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("select * from customer");
        ArrayList<String> customerIds = new ArrayList<>();

        while (resultSet.next()) {
            customerIds.add(resultSet.getString(1));
        }
        return customerIds;

    }
}
