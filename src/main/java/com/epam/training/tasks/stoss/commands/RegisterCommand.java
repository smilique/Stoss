package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.epam.training.tasks.stoss.entities.Attributes.*;

public class RegisterCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private static final String SUCCESSFUL_REGISTER = "controller?command=index";
    private static final String UNSUCCESSFUL_REGISTER = "controller?command=registerPage";

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter(USERNAME_ATTRIBUTE);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        String name = request.getParameter(NAME_ATTRIBUTE);
//TODO add hidden form, take user language from register page
        LOGGER.debug(login + " | " + password + " | " + name);

        AtomicReference<String> nextPage = new AtomicReference<>();
        HttpSession session = request.getSession();

        try {
            if (userService.validateLogin(login)) {
                LOGGER.debug("User already exists");
                session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "User already exists, please try another login.");
                nextPage.set(UNSUCCESSFUL_REGISTER);
            } else {
                LOGGER.debug("New user created");
                userService.register(login, password, name);
                session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "User created!");
                nextPage.set(SUCCESSFUL_REGISTER);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        String page = nextPage.get();

        return CommandResult.redirect(page);


    }

}
