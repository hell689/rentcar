package service;

import domain.Mark;
import service.exceptions.MarkNotUniqueException;
import service.exceptions.ServiceException;

import java.util.List;

public interface MarkService {

    Mark getMark(Long id);

    void deleteMark(Long id) throws ServiceException;

    void saveMark(Mark obj) throws MarkNotUniqueException, ServiceException;

    List<Mark> getMarks();
}
