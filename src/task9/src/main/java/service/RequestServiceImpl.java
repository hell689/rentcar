package service;

import dao.*;
import domain.Request;
import domain.User;
import service.exceptions.ServiceException;

import java.util.List;

public class RequestServiceImpl implements RequestService {

    private RequestDao requestDao;
    private MarkDao markDao;
    private ColorDao colorDao;
    private GearboxDao gearboxDao;
    private UserDao userDao;

    @Override
    public Request getRequest(Long id) {
        Request request = requestDao.read(id);
        request.setMark(markDao.read(request.getMark().getId()));
        request.setGearbox(gearboxDao.read(request.getGearbox().getId()));
        request.setColor(colorDao.read(request.getColor().getId()));
        request.setUser(userDao.read(request.getUser().getId()));
        return request;
    }

    @Override
    public void deleteRequest(Long id) throws ServiceException {
        try {
            requestDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveRequest(Request obj) throws ServiceException {
        try {
            if (obj.getId() == null) {
                requestDao.create(obj);
            } else {
                requestDao.update(obj);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Request> getRequests() {
        List<Request> requests = requestDao.findAll();
        for (Request request : requests) {
            request.setMark(markDao.read(request.getMark().getId()));
            request.setGearbox(gearboxDao.read(request.getGearbox().getId()));
            request.setColor(colorDao.read(request.getColor().getId()));
            request.setUser(userDao.read(request.getUser().getId()));
        }
        return requests;
    }

    @Override
    public List<Request> getUserRequests(User user) {
        List<Request> requests = requestDao.findUserRequests(user);
        for (Request request : requests) {
            request.setMark(markDao.read(request.getMark().getId()));
            request.setGearbox(gearboxDao.read(request.getGearbox().getId()));
            request.setColor(colorDao.read(request.getColor().getId()));
            request.setUser(userDao.read(request.getUser().getId()));
        }
        return requests;
    }
}
