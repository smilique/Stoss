package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.training.tasks.stoss.entities.Attributes.*;


public class UpdateSpecifiedCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(UpdateSpecifiedCommand.class);

    private static final String PAGE = "controller?command=users";

    private final UserService userService;

    public UpdateSpecifiedCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        String userIdText = request.getParameter(EDITED_USER_ID_ATTRIBUTE);
        Long userId = Long.valueOf(userIdText);
        try {
            if (password.length() > 3) {
                LOGGER.debug("updating password for user id: " + userId);
                userService.changePassword(userId, password);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Password must be longer than 3 symbols!");
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        return CommandResult.redirect(PAGE);
    }

}
