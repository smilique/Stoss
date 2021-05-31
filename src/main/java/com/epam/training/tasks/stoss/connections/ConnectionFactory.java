package com.epam.training.tasks.stoss.connections;

import com.epam.training.tasks.stoss.loaders.PropertyLoader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final static Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private final static String DB_PROPERTIES = "/db.properties";
    private final static String DRIVER_KEY = "database.driver";
    private final static String URL_KEY = "database.url";
    private final static String USER_KEY = "database.username";
    private final static String PASSWORD_KEY = "database.password";

    private String dbDriver;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;


    public ConnectionFactory()  {
        try {
            initializeProperties();
        } catch (ConnectionException e) {
            LOGGER.error(e);
        }
    }


    public Connection create() throws ConnectionException {
            try {
                Class.forName(dbDriver);
                Connection connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
                return connection;
            } catch (ClassNotFoundException | SQLException e) {
                LOGGER.error(e);
                throw new ConnectionException(e);
            }
    }

    private void initializeProperties() throws ConnectionException{
        try {
            Properties properties = new PropertyLoader().load(DB_PROPERTIES);
            dbDriver = properties.getProperty(DRIVER_KEY);
            dbUser = properties.getProperty(USER_KEY);
            dbPassword = properties.getProperty(PASSWORD_KEY);
            dbUrl = properties.getProperty(URL_KEY);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new ConnectionException(e);
        }

    }
}
