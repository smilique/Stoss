package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUsersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowUsersCommand.class);

    private static final String PAGE = "WEB-INF/view/users.jsp";

    private final UserService userService;

    public ShowUsersCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = null;
        try {
            users = userService.getAllUsers();
        } catch (ServiceException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        request.setAttribute("users",users);

        return CommandResult.forward(PAGE);
    }
}
