package com.epam.training.tasks.stoss.connections;

import com.epam.training.tasks.stoss.loaders.PropertyLoader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private final static AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private final static Lock INSTANCE_LOCK = new ReentrantLock();

    private final static String DB_PROPERTIES = "/db.properties";
    private final static String POOL_SIZE_KEY = "database.connectionPoolSize";

    private final PropertyLoader propertyLoader = new PropertyLoader();
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private final ConnectionFactory factory;
    private final Lock connectionsLock = new ReentrantLock();
    private final Semaphore connectionSemaphore;


    private int poolSize;

    private ConnectionPool() throws IOException {
        LOGGER.debug("creating ConnectionPool instance");
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
        factory = new ConnectionFactory();
        initializeProperties();
        initializeConnections();
        connectionSemaphore = new Semaphore(poolSize);

    }

    public static ConnectionPool getInstance() throws IOException {
        LOGGER.debug("ConnectionPool getInstance");
        ConnectionPool localInstance = INSTANCE.get();
        if (localInstance == null) {
            try {
                INSTANCE_LOCK.lock();
                localInstance = INSTANCE.get();
                if (localInstance == null) {
                    LOGGER.debug("New instance created");
                    //ConnectionFactory factory = new ConnectionFactory();
                    INSTANCE.getAndSet(new ConnectionPool());
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    private void initializeProperties() throws IOException {
        LOGGER.debug("initializing connection properties");
        Properties properties = propertyLoader.load(DB_PROPERTIES);
        String connectionPoolSize = properties.getProperty(POOL_SIZE_KEY);
        poolSize = Integer.parseInt(connectionPoolSize);
        LOGGER.info("Properties loaded");
    }

    private void initializeConnections() {
        try {
            for (int i = 0; i < poolSize; i++) {
                LOGGER.debug("initializing connections");
                //Class.forName(dbDriver);
                Connection connection = factory.create();
                ProxyConnection proxyConnection = new ProxyConnection(this,connection);
                availableConnections.add(proxyConnection);
                LOGGER.debug("added connection #" + i + " | " + proxyConnection);
            }
        } catch (SQLException | ClassNotFoundException | ConnectionException e) {
            LOGGER.error(e);
        }
    }


    public void returnConnection(ProxyConnection proxyConnection) {
        try {
            LOGGER.debug("returning connection " + proxyConnection);
            connectionsLock.lock();
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.add(proxyConnection);

                connectionSemaphore.release();
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() throws ConnectionException {
        try {
            connectionSemaphore.acquire();
            connectionsLock.lock();
            LOGGER.debug("locked");

            ProxyConnection proxyConnection = availableConnections.poll();
            LOGGER.debug("polled connection " + proxyConnection);
            connectionsInUse.add(proxyConnection);
            LOGGER.debug("getConnection " + proxyConnection);

            return proxyConnection;

        } catch (InterruptedException e) {
            LOGGER.error(e);
            throw new ConnectionException(e);
        } finally {
            connectionsLock.unlock();
        }
    }


}

