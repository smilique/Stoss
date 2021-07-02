package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.training.tasks.stoss.entities.Attributes.USERS_ATTRIBUTE;
import static com.epam.training.tasks.stoss.entities.Pages.USERS_PAGE;

public class ShowUsersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowUsersCommand.class);

    private final UserService userService;

    public ShowUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> users = null;
        users = userService.getAllUsers();

        request.setAttribute(USERS_ATTRIBUTE,users);

        return CommandResult.forward(USERS_PAGE);
    }
}
