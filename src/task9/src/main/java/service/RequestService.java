package service;

import domain.Request;
import domain.User;
import service.exceptions.ServiceException;

import java.util.List;

public interface RequestService {

    Request getRequest(Long id);

    void deleteRequest(Long id) throws ServiceException;

    void saveRequest(Request obj) throws ServiceException;

    List<Request> getRequests();

    List<Request> getUserRequests (User user);
}
