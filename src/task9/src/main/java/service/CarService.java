package service;

import domain.Car;
import domain.Request;
import service.exceptions.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CarService {

    Car getCar(Long id);

    void deleteCar(Long id) throws ServiceException;

    void saveCar(Car obj) throws ServiceException;

    List<Car> getCars();

    List<Car> getFreeCars(Date date);

    Map<String, List<Car>> getSuitableCars(Request request);
}
