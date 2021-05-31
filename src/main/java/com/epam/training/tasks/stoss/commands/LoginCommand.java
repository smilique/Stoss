package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.epam.training.tasks.stoss.entities.Attributes.*;

public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static final String SUCCESSFUL_LOGIN_PAGE = "controller?command=mainPage";
    private static final String UNSUCCESSFUL_LOGIN_PAGE = "controller?command=index";

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(USERNAME_ATTRIBUTE);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        Optional<User> optionalUser = null;
        try {
            optionalUser = userService.login(login,password);
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new CommandException(e);
        }
        AtomicReference<String> nextPage = new AtomicReference<>();
        HttpSession session = request.getSession();
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            LOGGER.debug(" current user: " + user.getName());
            String userLocale = user.getLocale();
            session.setAttribute(LOCALE_ATTRIBUTE, userLocale);
            session.setAttribute(USER_ATTRIBUTE, user);
            session.removeAttribute(ERROR_MESSAGE_ATTRIBUTE);
            nextPage.set(SUCCESSFUL_LOGIN_PAGE);
        } else {
            LOGGER.debug("Invalid login or password!");
            session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Invalid login or password!");
            nextPage.set(UNSUCCESSFUL_LOGIN_PAGE);
        }

        String page = nextPage.get();

        return CommandResult.redirect(page);

    }

}
