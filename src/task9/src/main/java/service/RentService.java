package service;

import domain.Rent;
import domain.User;
import service.exceptions.ServiceException;

import java.util.List;

public interface RentService {

    Rent getRent(Long id);

    void deleteRent(Long id) throws ServiceException;

    void saveRent(Rent obj) throws ServiceException;

    List<Rent> getRents();

    List<Rent> getUserRents(User user);
}
