package service;

import domain.Gearbox;
import service.exceptions.GearboxNotUniqueException;
import service.exceptions.ServiceException;

import java.util.List;

public interface GearboxService {

    Gearbox getGearbox(Long id);

    void deleteGearbox(Long id) throws ServiceException;

    void saveGearbox(Gearbox obj) throws GearboxNotUniqueException, ServiceException;

    List<Gearbox> getGearboxes();
}
