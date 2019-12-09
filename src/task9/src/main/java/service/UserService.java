package service;

import domain.User;
import service.exceptions.ServiceException;

import java.util.List;

public interface UserService {
    User getUser(Long id);

    User getByLoginAndPassword(String login, String password);

    User getByLogin(String login);

    void deleteUser(Long id) throws ServiceException;

    void saveUser(User obj) throws ServiceException;

    List<User> getUsers();
}
