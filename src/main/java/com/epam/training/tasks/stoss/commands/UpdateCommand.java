package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.training.tasks.stoss.entities.Attributes.*;


public class UpdateCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(UpdateCommand.class);

    private static final String PAGE = "controller?command=user";

    private final UserService userService;

    public UpdateCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(USER_ATTRIBUTE);
        Long id = currentUser.getId();
        try {
            if (password.length() > 3) {
                LOGGER.debug("updating password for user id: " + id);
                userService.changePassword(id, password);
            } else {
                session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Password must be longer than 3 symbols!");
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new CommandException(e);
        }
        return CommandResult.redirect(PAGE);
    }

}
