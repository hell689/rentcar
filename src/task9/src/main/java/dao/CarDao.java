package dao;

import domain.Car;
import domain.Request;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CarDao extends CrudDao<Long, Car> {

    List<Car> findAll();

    List<Car> findFreeCar(Date date);

    Map<String, List<Car>> findSuitableCars(Request request);
}
