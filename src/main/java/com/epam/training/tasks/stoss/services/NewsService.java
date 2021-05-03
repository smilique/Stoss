package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.dao.DaoException;
import com.epam.training.tasks.stoss.dao.DaoHelper;
import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.dao.NewsDao;
import com.epam.training.tasks.stoss.entities.News;
import com.epam.training.tasks.stoss.entities.NewsItem;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsService {

    private static final Logger LOGGER = Logger.getLogger(NewsService.class);

    private static final int SINGLE_PAGE = 1;

    private final DaoHelperFactory daoHelperFactory;

//    public NewsService() {
//        daoHelperFactory = new DaoHelperFactory();
//    }

    public NewsService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public News findNews(int currentPage, int recordsPerPage) throws Exception {

        int startIndex = currentPage * recordsPerPage - recordsPerPage;

        try (DaoHelper helper = daoHelperFactory.create()) {
            NewsDao newsDao = helper.createNewsDao();
            List<NewsItem> newsItems = newsDao.getAll();

            Collections.reverse(newsItems);
            News news = new News();
            if (newsItems.size() < recordsPerPage) {
                news.setAll(newsItems);
                news.setPagesCount(SINGLE_PAGE);
                return news;
            } else {
                int pagesCount = (int) Math.ceil((double) newsItems.size()/recordsPerPage);
                news.setPagesCount(pagesCount);
                if (startIndex + recordsPerPage < newsItems.size()) {
                    for (int i = startIndex; i < startIndex + recordsPerPage; i++) {
                        NewsItem item = newsItems.get(i);
                        news.addItem(item);
                        LOGGER.debug("selected news item #" + i);
                    }
                } else {
                        for (int i = startIndex; i < newsItems.size(); i++) {
                            NewsItem item = newsItems.get(i);
                            news.addItem(item);
                            LOGGER.debug("selected news item #" + i);
                        }
                    }
                return news;
                }

            } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

    }

}
