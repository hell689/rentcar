package service;

import dao.*;
import domain.Car;
import domain.Request;
import service.exceptions.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CarServiceImpl implements CarService {

    private CarDao carDao;
    private MarkDao markDao;
    private ColorDao colorDao;
    private GearboxDao gearboxDao;

    @Override
    public Car getCar(Long id) {
        Car car = carDao.read(id);
        car.setMark(markDao.read(car.getMark().getId()));
        car.setGearbox(gearboxDao.read(car.getGearbox().getId()));
        car.setColor(colorDao.read(car.getColor().getId()));
        return car;
    }

    @Override
    public void deleteCar(Long id) throws ServiceException {
        try {
            carDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveCar(Car obj) throws ServiceException {
        try {
            if (obj.getId() == null) {
                carDao.create(obj);
            } else {
                carDao.update(obj);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getCars() {
        List<Car> cars = carDao.findAll();
        for (Car car : cars) {
            car.setMark(markDao.read(car.getMark().getId()));
            car.setGearbox(gearboxDao.read(car.getGearbox().getId()));
            car.setColor(colorDao.read(car.getColor().getId()));
        }
        return cars;
    }

    @Override
    public List<Car> getFreeCars(Date date) {
        List<Car> cars = carDao.findFreeCar(date);
        for (Car car : cars) {
            car.setMark(markDao.read(car.getMark().getId()));
            car.setGearbox(gearboxDao.read(car.getGearbox().getId()));
            car.setColor(colorDao.read(car.getColor().getId()));
        }
        return cars;
    }

    @Override
    public Map<String, List<Car>> getSuitableCars(Request request) {
        Map<String, List<Car>> suitableCars = carDao.findSuitableCars(request);
        for (Map.Entry<String, List<Car>> entry : suitableCars.entrySet()) {
            for (Car car : entry.getValue()) {
                car.setMark(markDao.read(car.getMark().getId()));
                car.setGearbox(gearboxDao.read(car.getGearbox().getId()));
                car.setColor(colorDao.read(car.getColor().getId()));
            }
        }
        return suitableCars;
    }
}
