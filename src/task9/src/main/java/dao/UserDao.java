package dao;

import domain.User;

import java.util.List;

public interface UserDao extends CrudDao<Long, User> {

    List<User> findAll();

    User findByLoginAndPassword(String login, String password);

    User getByLogin(String login);
}
