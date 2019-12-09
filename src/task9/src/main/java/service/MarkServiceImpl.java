package service;

import dao.DaoException;
import dao.MarkDao;
import domain.Mark;
import service.exceptions.MarkNotUniqueException;
import service.exceptions.ServiceException;

import java.util.List;

public class MarkServiceImpl implements MarkService {

    private MarkDao markDao;

    @Override
    public Mark getMark(Long id) {
        return markDao.read(id);
    }

    @Override
    public void deleteMark(Long id) throws ServiceException {
        try {
            markDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveMark(Mark obj) throws ServiceException {
        try {
            if (obj.getId() == null) {
                if (markDao.getByMark(obj.getMark()) == null) {
                    markDao.create(obj);
                } else {
                    throw new MarkNotUniqueException(obj.getMark());
                }
            } else {
                markDao.update(obj);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Mark> getMarks() {
        return markDao.findAll();
    }
}
