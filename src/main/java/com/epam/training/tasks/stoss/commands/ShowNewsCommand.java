package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.News;
import com.epam.training.tasks.stoss.entities.NewsItem;
import com.epam.training.tasks.stoss.services.NewsService;
import com.epam.training.tasks.stoss.services.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.training.tasks.stoss.entities.Attributes.*;
import static com.epam.training.tasks.stoss.entities.Pages.NEWS_PAGE;


public class ShowNewsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowNewsCommand.class);

    private final NewsService newsService;

    public ShowNewsCommand(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int currentPage = Integer.parseInt(request.getParameter(PAGE_ATTRIBUTE));
        int numberOfItems = Integer.parseInt(request.getParameter(ITEMS_ATTRIBUTE));

        LOGGER.debug("currentPage: " + currentPage);

        try {
            News news = newsService.findNews(currentPage,numberOfItems);
            List<NewsItem> items = news.getAll();
            request.setAttribute(NEWS_ATTRIBUTE, items);
            request.setAttribute(CURRENT_PAGE_ATTRIBUTE, currentPage);

            int pagesCount = news.getPagesCount();
            LOGGER.debug("pages count: " + pagesCount);
            request.setAttribute(PAGES_ATTRIBUTE, pagesCount);

        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new CommandException(e);
        }

        return CommandResult.forward(NEWS_PAGE);
    }

}
