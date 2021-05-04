package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";
    private static final String SUCCESSFUL_LOGIN = "controller?command=mainPage";
    private static final String UNSUCCESSFUL_LOGIN = "controller?command=index";

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        LOGGER.debug(login + " | " + password);

        //Optional<User> optionalUser = Optional.empty();
        Optional<User> optionalUser = null;

        try {
            optionalUser = userService.login(login,password);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        AtomicReference<String> nextPage = new AtomicReference<>();

        HttpSession session = request.getSession();

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            LOGGER.debug(" current user: " + user.getName());
//            session.setAttribute("username", user.getName());
            String userLocale = user.getLocale();
            session.setAttribute("locale", userLocale);
            session.setAttribute("user", user);
            session.removeAttribute("errormessage");
            nextPage.set(SUCCESSFUL_LOGIN);
        } else {
            LOGGER.debug("Invalid login or password!");
            session.setAttribute("errormessage", "Invalid login or password!");
            nextPage.set(UNSUCCESSFUL_LOGIN);
        }

        String page = nextPage.get();

        return CommandResult.redirect(page);

    }

}
