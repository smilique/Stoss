package com.epam.training.tasks.stoss.commands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    private final static String PAGE = "controller?command=index";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        LOGGER.debug("Session invalidated");
        return CommandResult.forward(PAGE);
    }
}

