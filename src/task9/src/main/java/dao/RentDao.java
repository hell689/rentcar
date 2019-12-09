package dao;

import domain.Rent;
import domain.User;

import java.util.List;

public interface RentDao extends CrudDao<Long, Rent> {

    List<Rent> findAll();

    List<Rent> findUserRents(User user);
}
