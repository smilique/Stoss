package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.training.tasks.stoss.commands.Attributes.USERS_ATTRIBUTE;
import static com.epam.training.tasks.stoss.commands.Pages.RATING_PAGE;

public class ShowRatingCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowRatingCommand.class);

    private final UserService userService;

    public ShowRatingCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> users = null;
        users = userService.getRating();
        request.setAttribute(USERS_ATTRIBUTE,users);

        return CommandResult.forward(RATING_PAGE);
    }
}
