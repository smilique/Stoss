package com.epam.training.tasks.stoss.commands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.training.tasks.stoss.commands.Attributes.LOGIN_ATTRIBUTE;
import static com.epam.training.tasks.stoss.commands.Pages.USER_DELETE_CONFIRM_PAGE;

public class DeleteUserConfirmCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteUserConfirmCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Asking about user delete");
        String login = request.getParameter(LOGIN_ATTRIBUTE);
        request.setAttribute(LOGIN_ATTRIBUTE, login);
        return CommandResult.forward(USER_DELETE_CONFIRM_PAGE);
    }
}
