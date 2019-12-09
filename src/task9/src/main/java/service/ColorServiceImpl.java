package service;

import dao.ColorDao;
import dao.DaoException;
import domain.Color;
import service.exceptions.ColorNotUniqueException;
import service.exceptions.ServiceException;

import java.util.List;

public class ColorServiceImpl implements ColorService {

    private ColorDao colorDao;

    @Override
    public Color getColor(Long id) {
        return colorDao.read(id);
    }

    @Override
    public void deleteColor(Long id) throws ServiceException {
        try {
            colorDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveColor(Color obj) throws ServiceException {
        try {
            if (obj.getId() == null) {
                if (colorDao.getByColor(obj.getColor()) == null) {
                    colorDao.create(obj);
                } else {
                    throw new ColorNotUniqueException(obj.getColor());
                }
            } else {
                colorDao.update(obj);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Color> getColors() {
        return colorDao.findAll();
    }
}
