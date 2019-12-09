package dao.jdbc;

import dao.DaoException;
import dao.RentDao;
import domain.*;
import support.jdbc.JdbcDaoSupport;
import support.jdbc.RowMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RentJdbcDao extends JdbcDaoSupport implements RentDao {

    private RowMap<Rent> rowMap = new RowMap<Rent>() {
        @Override
        public Rent rowMap(ResultSet rs) throws SQLException {
            Rent rent = new Rent();
            rent.setId(rs.getLong("id"));
            rent.setRequest(new Request());
            rent.getRequest().setId(rs.getLong("request"));
            rent.setCar(new Car());
            rent.getCar().setId(rs.getLong("car"));
            rent.setStartDate(rs.getDate("start_date"));
            rent.setEndDate(rs.getDate("end_date"));
            return rent;
        }
    };

    @Override
    public void create(Rent obj) throws DaoException {
        Long id = getId("SELECT nextval('rents_id_seq')");
        obj.setId(id);
        update("INSERT INTO rents(id, request, car, start_date, end_date) VALUES (?, ?, ?, ?, ?)",
                obj.getId(), obj.getRequest().getId(), obj.getCar().getId(), new java.sql.Date(obj.getStartDate().getTime()),
                new java.sql.Date(obj.getEndDate().getTime()));
    }

    @Override
    public Rent read(Long id) {
        return selectOne("SELECT id, request, car, start_date, end_date FROM rents WHERE id = ?", rowMap, id);
    }

    @Override
    public void update(Rent obj) throws DaoException {
        update("UPDATE rents SET request = ?, car = ?, start_date = ?, end_date = ? WHERE id = ?",
                obj.getRequest(), obj.getCar(), new java.sql.Date(obj.getStartDate().getTime()),
                new java.sql.Date(obj.getEndDate().getTime()), obj.getId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        update("DELETE FROM rents WHERE id = ?", id);
    }

    @Override
    public List<Rent> findAll() {
        return selectList("SELECT id, request, car, start_date, end_date FROM rents", rowMap);
    }

    @Override
    public List<Rent> findUserRents(User user) {
        return selectList("SELECT id, request, car, start_date, end_date FROM rents WHERE request in (SELECT id FROM requests WHERE USER = ?)", rowMap, user.getId());
    }
}
