package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.training.tasks.stoss.commands.Attributes.LOGIN_ATTRIBUTE;

public class DeleteUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteUserCommand.class);
    private static final String PAGE = "controller?command=users";

    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN_ATTRIBUTE);
        LOGGER.debug("deleting user: " + login);
        userService.deleteUser(login);

        return CommandResult.forward(PAGE);
    }
}
