package dao;

import domain.Color;

import java.util.List;

public interface ColorDao extends CrudDao<Long, Color> {

    List<Color> findAll();

    Color getByColor(String color);
}
