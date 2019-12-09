package support.jdbc;

import dao.DaoException;
import support.jdbc.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDaoSupport {

    private ConnectionPool pool;

    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    public Long getId(String sql) {
        Long id = null;
        try (Connection con = getConnection(); Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public <T> T selectOne(String sql, RowMap<T> rowMap, Object... args) {
        T obj = null;
        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    obj = rowMap.rowMap(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public <T> List<T> selectList(String sql, RowMap<T> rowMap, Object... args) {
        List<T> objs = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    objs.add(rowMap.rowMap(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objs;
    }

    public <T> boolean update(String sql, Object... args) throws DaoException {
        boolean val = false;
        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            val = statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return val;
    }
}
