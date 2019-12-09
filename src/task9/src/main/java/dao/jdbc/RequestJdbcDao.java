package dao.jdbc;

import dao.DaoException;
import dao.RequestDao;
import domain.*;
import support.jdbc.JdbcDaoSupport;
import support.jdbc.RowMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestJdbcDao extends JdbcDaoSupport implements RequestDao {

    private RowMap<Request> rowMap = new RowMap<Request>() {
        @Override
        public Request rowMap(ResultSet rs) throws SQLException {
            Request request = new Request();
            request.setId(rs.getLong("id"));
            request.setMark(new Mark());
            request.getMark().setId(rs.getLong("mark"));
            request.setGearbox(new Gearbox());
            request.getGearbox().setId(rs.getLong("gearbox"));
            request.setVolume(rs.getFloat("volume"));
            request.setColor(new Color());
            request.getColor().setId(rs.getLong("color"));
            request.setStartDate(rs.getDate("start_date"));
            request.setEndDate(rs.getDate("end_date"));
            request.setComment(rs.getString("comment"));
            request.setUser(new User());
            request.getUser().setId(rs.getLong("user"));
            request.setProcessed(rs.getBoolean("processed"));
            return request;
        }
    };

    @Override
    public void create(Request obj) throws DaoException {
        Long id = getId("SELECT nextval('requests_id_seq')");
        obj.setId(id);
        update("INSERT INTO requests(id, mark, gearbox, volume, color, start_date, end_date, comment, user, processed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                obj.getId(), obj.getMark().getId(), obj.getGearbox().getId(), obj.getVolume(), obj.getColor().getId(),
                new java.sql.Date(obj.getStartDate().getTime()), new java.sql.Date(obj.getEndDate().getTime()),
                obj.getComment(), obj.getUser().getId(), obj.isProcessed());
    }

    @Override
    public Request read(Long id) {
        return selectOne("SELECT id, mark, gearbox, volume, color, start_date, end_date, comment, user, processed FROM requests " +
                "WHERE id = ?", rowMap, id);
    }

    @Override
    public void update(Request obj) throws DaoException {
        update("UPDATE requests SET mark = ?, gearbox = ?, volume = ?, color = ?,  start_date = ?, end_date = ?,  comment = ?, user = ?, processed = ? WHERE id = ?",
                obj.getMark().getId(), obj.getGearbox().getId(), obj.getVolume(), obj.getColor().getId(),
                new java.sql.Date(obj.getStartDate().getTime()), new java.sql.Date(obj.getEndDate().getTime()),
                obj.getComment(), obj.getUser().getId(), obj.isProcessed(), obj.getId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        update("DELETE FROM requests WHERE id = ?", id);
    }

    @Override
    public List<Request> findAll() {
        return selectList("SELECT id, mark, gearbox, volume, color, start_date, end_date, comment, user, processed FROM requests",
                rowMap);
    }

    @Override
    public List<Request> findUserRequests(User user) {
        return selectList("SELECT id, mark, gearbox, volume, color, start_date, end_date, comment, user, processed FROM requests WHERE user = ?",
                rowMap, user.getId());
    }
}
