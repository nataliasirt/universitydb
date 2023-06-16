package com.solvd.university.util;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool implements IConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final Properties PROPERTIES = new Properties();
    static {
        try {
            FileReader reader = new FileReader("src/main/resources/dababase.properties");
            PROPERTIES.load(reader);
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }
    private String url = PROPERTIES.getProperty("url");
    private String user = PROPERTIES.getProperty("user");
    private String password = PROPERTIES.getProperty("password");
    private static final int INITIAL_POOL_SIZE = 10;
    private static List<Connection> connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);

    private List<Connection> usedConnections = new ArrayList<>();

    private static ConnectionPool INSTANCE = null;
    private ConnectionPool(){
    }

    public ConnectionPool(String url, String user, String password, List<Connection> usedConnections) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.usedConnections = usedConnections;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() -1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<Connection> getConnectionPool() {
        return connectionPool;
    }

    public static void setConnectionPool(List<Connection> connectionPool) {
        ConnectionPool.connectionPool = connectionPool;
    }

    public List<Connection> getUsedConnections() {
        return usedConnections;
    }

    public void setUsedConnections(List<Connection> usedConnections) {
        this.usedConnections = usedConnections;
    }

    public static ConnectionPool getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(ConnectionPool INSTANCE) {
        ConnectionPool.INSTANCE = INSTANCE;
    }
}
