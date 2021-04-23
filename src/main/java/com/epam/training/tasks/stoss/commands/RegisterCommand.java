package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class RegisterCommand implements Command{

    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";
    private static final String NAME_PARAM = "name";
    private static final String SUCCESSFUL_REGISTER = "controller?command=index";
    private static final String UNSUCCESSFUL_REGISTER = "controller?command=registerPage";

    private final UserService userService;

    public RegisterCommand (UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String name = request.getParameter(NAME_PARAM);

        LOGGER.debug(login + " | " + password + " | " + name);

        AtomicReference<String> nextPage = new AtomicReference<>();
        HttpSession session = request.getSession();

        try {
            if (userService.validateLogin(login)) {
                LOGGER.debug("User already exists");
                session.setAttribute("errormessage", "User already exists, please try another login.");
                nextPage.set(UNSUCCESSFUL_REGISTER);
            } else {
                LOGGER.debug("New user created");
                session.setAttribute("errormessage", "User created! Please login.");
                nextPage.set(SUCCESSFUL_REGISTER);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        String page = nextPage.get();

        return CommandResult.redirect(page);


    }

}
