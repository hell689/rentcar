package service;

import dao.DaoException;
import dao.GearboxDao;
import domain.Gearbox;
import service.exceptions.GearboxNotUniqueException;
import service.exceptions.ServiceException;

import java.util.List;

public class GearboxServiceImpl implements GearboxService {

    private GearboxDao gearboxDao;

    @Override
    public Gearbox getGearbox(Long id) {
        return gearboxDao.read(id);
    }

    @Override
    public void deleteGearbox(Long id) throws ServiceException {
        try {
            gearboxDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveGearbox(Gearbox obj) throws ServiceException {
        try {
            if (obj.getId() == null) {
                if (gearboxDao.getByGearbox(obj.getGearbox()) == null) {
                    gearboxDao.create(obj);
                } else {
                    throw new GearboxNotUniqueException(obj.getGearbox());
                }
            } else {
                gearboxDao.update(obj);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Gearbox> getGearboxes() {
        return gearboxDao.findAll();
    }
}
