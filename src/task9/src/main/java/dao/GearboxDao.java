package dao;

import domain.Gearbox;

import java.util.List;

public interface GearboxDao extends CrudDao<Long, Gearbox> {

    List<Gearbox> findAll();

    Gearbox getByGearbox(String gearbox);
}
