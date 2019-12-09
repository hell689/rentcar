package support.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMap<T> {

    T rowMap(ResultSet rs) throws SQLException;
}
