package dao.jdbc;

import dao.CarDao;
import dao.DaoException;
import domain.*;
import support.jdbc.JdbcDaoSupport;
import support.jdbc.RowMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CarJdbcDao extends JdbcDaoSupport implements CarDao {

    private RowMap<Car> rowMap = new RowMap<Car>() {
        @Override
        public Car rowMap(ResultSet rs) throws SQLException {
            Car car = new Car();
            car.setId(rs.getLong("id"));
            car.setMark(new Mark());
            car.getMark().setId(rs.getLong("mark"));
            car.setGearbox(new Gearbox());
            car.getGearbox().setId(rs.getLong("gearbox"));
            car.setVolume(rs.getFloat("volume"));
            car.setColor(new Color());
            car.getColor().setId(rs.getLong("color"));
            return car;
        }
    };

    @Override
    public void create(Car obj) throws DaoException {
        Long id = getId("SELECT nextval('cars_id_seq')");
        obj.setId(id);
        update("INSERT INTO cars(id, mark, gearbox, volume, color) VALUES (?, ?, ?, ?, ?)", obj.getId(),
                obj.getMark().getId(), obj.getGearbox().getId(), obj.getVolume(), obj.getColor().getId());
    }

    @Override
    public Car read(Long id) {
        return selectOne("SELECT id, mark, gearbox, volume, color FROM cars WHERE id = ?", rowMap, id);
    }

    @Override
    public void update(Car obj) throws DaoException {
        update("UPDATE cars SET mark = ?, gearbox = ?, volume = ?, color = ? WHERE id = ?",
                obj.getMark().getId(), obj.getGearbox().getId(), obj.getVolume(), obj.getColor().getId(), obj.getId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        update("DELETE FROM cars WHERE id = ?", id);
    }

    @Override
    public List<Car> findAll() {
        return selectList("SELECT id, mark, gearbox, volume, color FROM cars", rowMap);
    }

    @Override
    public List<Car> findFreeCar(Date date) {
        return selectList("SELECT id, mark, gearbox, volume, color FROM cars where id NOT IN (SELECT car FROM rents where end_date > ?)", rowMap, date);
    }

    public Map<String, List<Car>> findSuitableCars(Request request) {
        Map<String, List<Car>> suitableCars = new HashMap<>();
        if (request.getColor() != null) {
            List<Car> sameColorCars = selectList("SELECT id, mark, gearbox, volume, color FROM cars where color = ?", rowMap, request.getColor().getId());
            if (sameColorCars.size() > 0)
                suitableCars.put("Color", sameColorCars);
        }
        if (request.getGearbox() != null) {
            List<Car> sameGearboxCars = selectList("SELECT id, mark, gearbox, volume, color FROM cars where gearbox = ?", rowMap, request.getGearbox().getId());
            if (sameGearboxCars.size() > 0)
                suitableCars.put("Gearbox", sameGearboxCars);
        }
        if (request.getMark() != null) {
            List<Car> sameMarkCars = selectList("SELECT id, mark, gearbox, volume, color FROM cars where mark = ?", rowMap, request.getMark().getId());
            if (sameMarkCars.size() > 0)
                suitableCars.put("Mark", sameMarkCars);
        }
        if (request.getVolume() != 0f) {
            List<Car> sameMarkCars = selectList("SELECT id, mark, gearbox, volume, color FROM cars where volume = ?", rowMap, request.getVolume());
            if (sameMarkCars.size() > 0)
                suitableCars.put("Volume", sameMarkCars);
        }

        Map<Car, Integer> mostSuitableCars = new HashMap<>();
        for (Map.Entry<String, List<Car>> entry : suitableCars.entrySet()) {
            for (Car car : entry.getValue()) {
                Integer count = mostSuitableCars.get(car);
                if (count == null) {
                    count = 0;
                }
                mostSuitableCars.put(car, ++count);
            }
        }
        List<Car> sameCars = new ArrayList<>();
        for (Map.Entry<Car, Integer> entry : mostSuitableCars.entrySet()) {
            if (entry.getValue() >= 2)
                sameCars.add(entry.getKey());
        }
        suitableCars.put("Most suitable cars", sameCars);
        return suitableCars;
    }

}
