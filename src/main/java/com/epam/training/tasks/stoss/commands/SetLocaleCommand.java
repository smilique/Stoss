package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;

public class SetLocaleCommand implements Command{

    private static final Logger LOGGER = Logger.getLogger(SetLocaleCommand.class);

    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String LANGUAGE_TAG_PARAMETER = "languageTag";
    private static final String INDEX_PAGE = "controller?command=index";
    private static final String MAIN_PAGE = "controller?command=mainPage";

    private final UserService userService;


    public SetLocaleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String languageTag = request.getParameter(LANGUAGE_TAG_PARAMETER);
        Locale locale = Locale.forLanguageTag(languageTag);
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE_ATTRIBUTE, locale);
        response.setLocale(locale);
        String page = INDEX_PAGE;

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            try {
                Optional<User> updatedUser = userService.changeLocale(user.getId(), languageTag);
                session.setAttribute("user", updatedUser.get());
                page = MAIN_PAGE;
            } catch (ServiceException e) {
                LOGGER.error(e);
                e.printStackTrace();
            }
        }

        return CommandResult.forward(page);
    }
}
