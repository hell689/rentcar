package service;

import dao.DaoException;
import dao.UserDao;
import domain.User;
import service.exceptions.LoginNotUniqueException;
import service.exceptions.ServiceException;
import support.utils.Crypto;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User getUser(Long id) {
        return userDao.read(id);
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        password = Crypto.hash(password);
        return userDao.findByLoginAndPassword(login, password);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public void deleteUser(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveUser(User obj) throws ServiceException {
        obj.setPassword(Crypto.hash(obj.getPassword()));
        try {
            if (obj.getId() == null) {
                if (userDao.getByLogin(obj.getLogin()) == null) {
                    userDao.create(obj);
                } else {
                    throw new LoginNotUniqueException(obj.getLogin());
                }
            } else {
                userDao.update(obj);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }
}
