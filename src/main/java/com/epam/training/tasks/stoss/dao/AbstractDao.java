package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.Entity;
import com.epam.training.tasks.stoss.mappers.RowMapper;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <T extends Entity> implements Dao<T> {

    private final static Logger LOGGER = Logger.getLogger(AbstractDao.class);

    private static final String GET_ALL_QUERY = "select * from ";

    private final ProxyConnection connection;
    private final RowMapper<T> mapper;

    protected AbstractDao(ProxyConnection connection, RowMapper<T> mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();

            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i+1,params[i]);
        }
        LOGGER.debug("Statement " + statement);
        return statement;
    }

    public List<T> getAll() throws DaoException {
        String table = getTableName();

        return executeQuery(GET_ALL_QUERY + table);
    }

    public List<T> getAll(String query) throws DaoException {
        return executeQuery(query);
    }

    protected Optional<T> executeForSingleResult (String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    protected void executeUpdate (String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected abstract String getTableName();

}
