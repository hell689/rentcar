package dao;

import domain.Request;
import domain.User;

import java.util.List;

public interface RequestDao extends CrudDao<Long, Request> {

    List<Request> findAll();

    List<Request> findUserRequests(User user);
}
