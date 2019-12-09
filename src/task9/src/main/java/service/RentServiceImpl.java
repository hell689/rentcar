package service;

import dao.DaoException;
import dao.RentDao;
import domain.Rent;
import domain.User;
import service.exceptions.ServiceException;

import java.util.List;

public class RentServiceImpl implements RentService {

    private RentDao rentDao;
    private RequestService requestService;
    private CarService carService;


    @Override
    public Rent getRent(Long id) {
        Rent rent = rentDao.read(id);
        rent.setRequest(requestService.getRequest(rent.getRequest().getId()));
        rent.setCar(carService.getCar(rent.getCar().getId()));
        return rent;
    }

    @Override
    public void deleteRent(Long id) throws ServiceException {
        try {
            rentDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveRent(Rent obj) throws ServiceException {
        try {
            if (obj.getId() == null) {
                rentDao.create(obj);
            } else {
                rentDao.update(obj);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Rent> getRents() {
        List<Rent> rents = rentDao.findAll();
        for (Rent rent : rents) {
            rent.setRequest(requestService.getRequest(rent.getRequest().getId()));
            rent.setCar(carService.getCar(rent.getCar().getId()));
        }
        return rents;
    }

    @Override
    public List<Rent> getUserRents(User user) {
        List<Rent> rents = rentDao.findUserRents(user);
        for (Rent rent : rents) {
            rent.setRequest(requestService.getRequest(rent.getRequest().getId()));
            rent.setCar(carService.getCar(rent.getCar().getId()));
        }
        return rents;
    }
}
