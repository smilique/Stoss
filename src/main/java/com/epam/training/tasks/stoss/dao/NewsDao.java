package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.NewsItem;
import com.epam.training.tasks.stoss.mappers.NewsRowMapper;

import java.sql.Date;
import java.util.Optional;

public class NewsDao extends AbstractDao<NewsItem> {

    private static final String TABLE = NewsItem.TABLE;
    private static final String POST_QUERY = "insert into news set caption = ?, text = ?, time = ?";
    private static final String READ_QUERY = "select * from " + TABLE +" where id = ?";
    private static final String REMOVE_QUERY = "delete from news where id = ?";


    protected NewsDao(ProxyConnection connection) {
        super(connection, new NewsRowMapper());
    }

    @Override
    protected String getTableName() {
        return NewsItem.TABLE;
    }

    @Override
    public Optional<NewsItem> findById(Long id) throws DaoException {
        return executeForSingleResult(READ_QUERY, id);
    }

    @Override
    public void save(NewsItem item) throws DaoException {
        String caption = item.getCaption();
        String text = item.getText();
        Date date = item.getDate();
        executeUpdate(POST_QUERY, caption, text, date);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_QUERY, id);
    }
}
