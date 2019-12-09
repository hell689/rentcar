package support.jdbc.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private static final String DATABASE_PROPERTIES_PATH = "src/task9/database.properties";
    private int min;
    private int max;
    private String driver;
    private String url;
    private String login;
    private String passwd;

    Queue<PooledConnection> free = new ConcurrentLinkedQueue<>();
    List<PooledConnection> used = Collections.synchronizedList(new ArrayList<PooledConnection>());

    public ConnectionPool() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(DATABASE_PROPERTIES_PATH);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url");
        login = properties.getProperty("login");
        passwd = properties.getProperty("password");
        max = Integer.parseInt(properties.getProperty("max"));
        min = Integer.parseInt(properties.getProperty("min"));

        for (int i = 0; i < min; i++) {
            free.add(getPooledConnection());
        }
    }

    private PooledConnection getPooledConnection() {
        try {
            Connection con = DriverManager.getConnection(url, login, passwd);
            PooledConnection pcon = new PooledConnection(this, con);
            return pcon;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Connection getConnection() {
        PooledConnection pcon = null;
        if (!free.isEmpty()) {
            pcon = free.remove();
        } else if (used.size() < max) {
            pcon = getPooledConnection();
        } else {
            throw new RuntimeException("Невозможно создать соединение");
        }
        used.add(pcon);
        return pcon;
    }

    public void destroy() {
        for (PooledConnection pcon : free) {
            try {
                pcon.getOriginal().close();
            } catch (Exception ignored) {
            }
        }
        for (PooledConnection pcon : used) {
            try {
                pcon.getOriginal().close();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }
}
