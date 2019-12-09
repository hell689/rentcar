package dao.jdbc;

import dao.DaoException;
import dao.UserDao;
import domain.Role;
import domain.User;
import support.jdbc.JdbcDaoSupport;
import support.jdbc.RowMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcDao extends JdbcDaoSupport implements UserDao {

    private RowMap<User> rowMap = new RowMap<User>() {
        @Override
        public User rowMap(ResultSet rs) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setAddress(rs.getString("address"));
            user.setRole(Role.values()[rs.getInt("role")]);
            return user;
        }
    };

    @Override
    public List<User> findAll() {
        return selectList("SELECT id, name, surname, login, password, address, role FROM users", rowMap);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return selectOne("SELECT id, name, surname, login, password, address, role FROM users WHERE login = ? " +
                "AND password = ?", rowMap, login, password);
    }

    @Override
    public User getByLogin(String login) {
        return selectOne("SELECT id, name, surname, login, password, address, role FROM users WHERE login = ?",
                rowMap, login);
    }

    @Override
    public void create(User obj) throws DaoException {
        Long id = getId("SELECT nextval('users_id_seq')");
        obj.setId(id);
        update("INSERT INTO users(id, name, surname, login, password, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)",
                obj.getId(), obj.getName(), obj.getSurname(), obj.getLogin(), obj.getPassword(), obj.getAddress(), obj.getRole().ordinal());
    }

    @Override
    public User read(Long id) {
        return selectOne("SELECT id, name, surname, login, password, address, role FROM users WHERE id = ?", rowMap, id);
    }

    @Override
    public void update(User obj) throws DaoException {
        update("UPDATE users SET name = ?, surname = ?, login = ?, password = ?, address = ?, role = ? WHERE id = ?",
                obj.getName(), obj.getSurname(), obj.getLogin(), obj.getPassword(), obj.getAddress(),
                obj.getRole().ordinal(), obj.getId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        update("DELETE FROM users WHERE id = ?", id);
    }
}
