package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.services.NewsService;
import com.epam.training.tasks.stoss.services.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteNewsItemCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteNewsItemCommand.class);

    private static final String PAGE = "controller?command=news&page=1&items=2";

    private NewsService newsService;

    public DeleteNewsItemCommand(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String newsId = request.getParameter("newsItemId");
        LOGGER.debug("deleting news item id: " + newsId);
        Long id = Long.valueOf(
                Integer.parseInt(newsId));
        newsService.deleteItem(id);
        return CommandResult.forward(PAGE);
    }
}
