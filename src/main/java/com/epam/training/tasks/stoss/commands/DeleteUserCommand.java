package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteUserCommand.class);
    private static final String PAGE = "controller?command=users";

    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        try {
            userService.deleteUser(login);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }


        return CommandResult.forward(PAGE);
    }
}
