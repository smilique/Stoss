package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.training.tasks.stoss.entities.Attributes.USERS_ATTRIBUTE;
import static com.epam.training.tasks.stoss.entities.Pages.RATING_PAGE;

public class ShowRatingCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowRatingCommand.class);

//    private static final String PAGE = "WEB-INF/view/users.jsp";

    private final UserService userService;

    public ShowRatingCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = null;
        try {
            users = userService.getRating();
//TODO add place column to users (in jsp, I think)
        } catch (ServiceException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        request.setAttribute(USERS_ATTRIBUTE,users);

        return CommandResult.forward(RATING_PAGE);
    }
}
