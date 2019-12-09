package dao;

public interface CrudDao<ID, T> extends Dao<T> {

    void create(T obj) throws DaoException;

    T read(ID id);

    void update(T obj) throws DaoException;

    void delete(ID id) throws DaoException;
}
