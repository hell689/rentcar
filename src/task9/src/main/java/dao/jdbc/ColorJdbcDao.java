package dao.jdbc;

import dao.ColorDao;
import dao.DaoException;
import domain.Color;
import support.jdbc.JdbcDaoSupport;
import support.jdbc.RowMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ColorJdbcDao extends JdbcDaoSupport implements ColorDao {

    private RowMap<Color> rowMap = new RowMap<Color>() {
        @Override
        public Color rowMap(ResultSet rs) throws SQLException {
            Color color = new Color();
            color.setId(rs.getLong("id"));
            color.setColor(rs.getString("color"));
            return color;
        }
    };

    @Override
    public List<Color> findAll() {
        return selectList("SELECT id, color FROM colors", rowMap);
    }

    @Override
    public Color getByColor(String color) {
        return selectOne("SELECT id, color FROM colors WHERE color = ?", rowMap, color);
    }

    @Override
    public void create(Color obj) throws DaoException {
        Long id = getId("SELECT nextval('colors_id_seq')");
        obj.setId(id);
        update("INSERT INTO colors(id, color) VALUES (?, ?)", obj.getId(), obj.getColor());
    }

    @Override
    public Color read(Long id) {
        return selectOne("SELECT id, color FROM colors WHERE id = ?", rowMap, id);
    }

    @Override
    public void update(Color obj) throws DaoException {
        update("UPDATE colors SET color = ? WHERE id = ?", obj.getColor(), obj.getId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        update("DELETE FROM colors WHERE id = ?", id);
    }
}
