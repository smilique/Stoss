package com.epam.training.tasks.stoss.commands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(ShowPageCommand.class);
    private String page;

    public ShowPageCommand(final String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug(" Showing page: " + page);
        LOGGER.debug("Browser preferrable locale :" + request.getLocales().nextElement());
        return CommandResult.forward(page);
    }
}
