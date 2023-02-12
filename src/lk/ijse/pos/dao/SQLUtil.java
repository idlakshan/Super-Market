package lk.ijse.pos.dao;

import lk.ijse.pos.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {

    private static PreparedStatement prepareStatement(String sql,Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            prepareStatement.setObject(i+1,args[i]);
        }
        return prepareStatement;
    }

    public static boolean executeUpdate(String sql,Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = prepareStatement(sql, args);
        return prepareStatement.executeUpdate() > 0;

    }

    public static ResultSet executeQuery(String sql,Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = prepareStatement(sql, args);
        ResultSet resultSet = prepareStatement.executeQuery();
        return resultSet;

    }

}
