package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.epam.training.tasks.stoss.entities.Pages.USER_EDIT_PAGE;

public class EditAnotherUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditAnotherUserCommand.class);

    private final UserService userService;

    public EditAnotherUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


        String editedUserLogin = request.getParameter("login");
        try {
            Optional<User> optionalUser = userService.getInfo(editedUserLogin);
            User editedUser = optionalUser.get();
            request.setAttribute("editedUser", editedUser);
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new CommandException(e);
        }


        return CommandResult.forward(USER_EDIT_PAGE);
    }
}
