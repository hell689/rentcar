package dao;

import domain.Mark;

import java.util.List;

public interface MarkDao extends CrudDao<Long, Mark> {

    List<Mark> findAll();

    Mark getByMark(String mark);
}
