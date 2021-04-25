package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.dao.DaoException;
import com.epam.training.tasks.stoss.dao.DaoHelper;
import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.dao.NewsDao;
import com.epam.training.tasks.stoss.entities.NewsItem;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NewsService {

    private static final Logger LOGGER = Logger.getLogger(NewsService.class);
    private final DaoHelperFactory daoHelperFactory;

    public NewsService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public List<NewsItem> findNews(int currentPage, int recordsPerPage) throws Exception {

        int startIndex = currentPage * recordsPerPage - recordsPerPage;

        try (DaoHelper helper = daoHelperFactory.create()) {
            NewsDao newsDao = helper.createNewsDao();
            List<NewsItem> newsItem = newsDao.getAll();
            List<NewsItem> newsResult = new ArrayList<>();
            for (int i = startIndex; i < startIndex + recordsPerPage; i++) {
                NewsItem item = newsItem.get(i);
                newsResult.add(item);
            }
            return newsResult;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

    }

}
