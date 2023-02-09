package dao;

import db.DbConnection;
import model.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> {

    public boolean save(T dto) throws SQLException, ClassNotFoundException;

    public boolean update(T dto) throws SQLException, ClassNotFoundException;

    public T search(ID id) throws SQLException, ClassNotFoundException;

    public boolean delete(ID id) throws SQLException, ClassNotFoundException;

    public ArrayList<T> loadAll() throws SQLException, ClassNotFoundException;

    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;
}
