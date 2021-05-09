package com.epam.training.tasks.stoss.commands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserConfirmCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteUserConfirmCommand.class);

    private static final String PAGE = "";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        request.setAttribute("login", login);
        return CommandResult.forward(PAGE);
    }
}
