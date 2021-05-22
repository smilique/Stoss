package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.mappers.UserRowMapper;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User>{

    private final static Logger LOGGER = Logger.getLogger(UserDao.class);

    private static final String SELECT = "select ";
    private static final String UPDATE = "update ";
    private static final String INSERT = "insert into ";
    private static final String WHERE = "where ";
    private static final String TABLE = User.TABLE;

    private static final String USER_ID = "user.id = ?";

    private static final String USER_READ_COLUMNS = "user.login, user.id, user.balance, " +
            "user.name, user.points, user.locale, user.userpic, r.name " +
            "from user inner join role r on user.ROLE_ID = r.ID ";

    private static final String ADD_NEW_QUERY = INSERT + " user set login = ?, password = md5(?), name = ?";
    private static final String VALIDATE_QUERY = "select " + USER_READ_COLUMNS + "where login = ?";
    private static final String LOGIN_QUERY = VALIDATE_QUERY + " and password = MD5(?)";
    private static final String UPDATE_BALANCE_QUERY = "update user set balance = ? where id = ?";
    private static final String UPDATE_POINTS_QUERY = "update user set points = ? where id = ?";
    private static final String UPDATE_USERPIC_QUERY = "update user set userpic = ? where id = ?";
    private static final String UPDATE_PASSWORD_QUERY = "update user set password = md5(?) where id = ?";
    private static final String UPDATE_LOCALE_QUERY = "update user set locale = ? where id = ?";
    private static final String GET_BY_ID_QUERY = SELECT + USER_READ_COLUMNS + WHERE + USER_ID;
    private static final String GET_ALL_QUERY = SELECT + USER_READ_COLUMNS;
    private static final String DELETE_USER_QUERY = "delete from user where login = ?";
    private static final String GET_BY_POINTS_QUERY = SELECT + USER_READ_COLUMNS + "order by user.points desc";

    protected UserDao(ProxyConnection connection) {
        super(connection, new UserRowMapper());
    }


    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        LOGGER.debug("checking password for: " + login);
        return executeForSingleResult(LOGIN_QUERY, login, password);
    }

    public Optional<User> findUserByLogin(String login) throws DaoException {
        LOGGER.debug("finging user with login: " + login);
        return executeForSingleResult(VALIDATE_QUERY, login);
    }

    public void addNewUser(String login, String password, String name) throws DaoException {
            LOGGER.debug("adding new user");
            executeUpdate(ADD_NEW_QUERY, login, password, name);
    }

    public void updateBalance(Long id, BigDecimal deposit) throws DaoException {
        LOGGER.debug("updating balance");
        executeUpdate(UPDATE_BALANCE_QUERY, deposit, id);
    }

    public void updatePoints(Long id, Long points) throws DaoException {
        LOGGER.debug("updating points");
        executeUpdate(UPDATE_POINTS_QUERY, points, id);
    }

    public void updatePassword(Long id, String password) throws DaoException {
        LOGGER.debug("Updating password for user id: " + id);
        executeUpdate(UPDATE_PASSWORD_QUERY, password, id);
    }

    public void updateUserpic(Long id, String path) throws DaoException {
        executeUpdate(UPDATE_USERPIC_QUERY, path, id);
    }

    public void deleteUser(String login) throws DaoException {
        executeUpdate(DELETE_USER_QUERY, login);
    }

    public List<User> getByPoints() throws DaoException {
        return super.getAll(GET_BY_POINTS_QUERY);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return super.getAll(GET_ALL_QUERY);
    }

    @Override
    protected String getTableName() {
        return User.TABLE;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        //return user by id
        LOGGER.debug("getting user by id: " + id);
        return executeForSingleResult(GET_BY_ID_QUERY, id);
    }

    @Override
    public void save(User user) throws DaoException {
        //save user into db
        if (user.getId() == null) {
            create(user);
        } else {
            update(user);
        }
    }

    private void create(User user) throws DaoException {
        executeUpdate(ADD_NEW_QUERY,user);
    }

    private void update(User user) throws DaoException {
        Optional<User> optionalUser = findById(user.getId());
        executeUpdate(UPDATE, optionalUser);
    }

    @Override
    public void removeById(Long id) {
        //remove user from db
    }

    public void updateLocale(Long userId, String languageTag) throws DaoException {
        executeUpdate(UPDATE_LOCALE_QUERY, languageTag, userId);
    }
}
