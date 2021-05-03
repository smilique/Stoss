package com.epam.training.tasks.stoss.dao;

import com.epam.training.tasks.stoss.connections.ProxyConnection;
import com.epam.training.tasks.stoss.entities.NewsItem;
import com.epam.training.tasks.stoss.mappers.NewsRowMapper;

import java.util.Optional;

public class NewsDao extends AbstractDao<NewsItem> {

    private static final String READ_QUERY = "select * from " + NewsItem.TABLE +" where id = ?";

    protected NewsDao(ProxyConnection connection) {
        super(connection, new NewsRowMapper());
    }

    @Override
    protected String getTableName() {
        return NewsItem.TABLE;
    }

    @Override
    public Optional<NewsItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(NewsItem item) throws DaoException {

    }

    @Override
    public void removeById(Long id) {

    }
}
