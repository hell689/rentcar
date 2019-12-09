package service;

import domain.Color;
import service.exceptions.ServiceException;

import java.util.List;

public interface ColorService {

    Color getColor(Long id);

    void deleteColor(Long id) throws ServiceException;

    void saveColor(Color obj) throws ServiceException;

    List<Color> getColors();
}
