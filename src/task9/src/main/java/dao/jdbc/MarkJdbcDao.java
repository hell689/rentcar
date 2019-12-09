package dao.jdbc;

import dao.DaoException;
import dao.MarkDao;
import domain.Mark;
import support.jdbc.JdbcDaoSupport;
import support.jdbc.RowMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MarkJdbcDao extends JdbcDaoSupport implements MarkDao {

    private RowMap<Mark> rowMap = new RowMap<Mark>() {
        @Override
        public Mark rowMap(ResultSet rs) throws SQLException {
            Mark mark = new Mark();
            mark.setId(rs.getLong("id"));
            mark.setMark(rs.getString("mark"));
            return mark;
        }
    };

    @Override
    public List<Mark> findAll() {
        return selectList("SELECT id, mark FROM marks", rowMap);
    }

    @Override
    public Mark getByMark(String mark) {
        return selectOne("SELECT id, mark FROM marks WHERE mark = ?", rowMap, mark);
    }

    @Override
    public void create(Mark obj) throws DaoException {
        Long id = getId("SELECT nextval('marks_id_seq')");
        obj.setId(id);
        update("INSERT INTO marks(id, mark) VALUES (?, ?)", obj.getId(), obj.getMark());
    }

    @Override
    public Mark read(Long id) {
        return selectOne("SELECT id, mark FROM marks WHERE id = ?", rowMap, id);
    }

    @Override
    public void update(Mark obj) throws DaoException {
        update("UPDATE marks SET mark = ? WHERE id = ?", obj.getMark(), obj.getId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        update("DELETE FROM marks WHERE id = ?", id);
    }
}
