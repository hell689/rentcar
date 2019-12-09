package dao.jdbc;

import dao.DaoException;
import dao.GearboxDao;
import domain.Gearbox;
import support.jdbc.JdbcDaoSupport;
import support.jdbc.RowMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GearboxJdbcDao extends JdbcDaoSupport implements GearboxDao {

    private RowMap<Gearbox> rowMap = new RowMap<Gearbox>() {
        @Override
        public Gearbox rowMap(ResultSet rs) throws SQLException {
            Gearbox gearbox = new Gearbox();
            gearbox.setId(rs.getLong("id"));
            gearbox.setGearbox(rs.getString("gearbox"));
            return gearbox;
        }
    };

    @Override
    public List<Gearbox> findAll() {
        return selectList("SELECT id, gearbox FROM gearboxes", rowMap);
    }

    @Override
    public Gearbox getByGearbox(String gearbox) {
        return selectOne("SELECT id, gearbox FROM gearboxes WHERE gearbox = ?", rowMap, gearbox);
    }

    @Override
    public void create(Gearbox obj) throws DaoException {
        Long id = getId("SELECT nextval('gearboxes_id_seq')");
        obj.setId(id);
        update("INSERT INTO gearboxes(id, gearbox) VALUES (?, ?)", obj.getId(), obj.getGearbox());
    }

    @Override
    public Gearbox read(Long id) {
        return selectOne("SELECT id, gearbox FROM gearboxes WHERE id = ?", rowMap, id);
    }

    @Override
    public void update(Gearbox obj) throws DaoException {
        update("UPDATE gearboxes SET gearbox = ? WHERE id = ?", obj.getGearbox(), obj.getId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        update("DELETE FROM gearboxes WHERE id = ?", id);
    }
}
