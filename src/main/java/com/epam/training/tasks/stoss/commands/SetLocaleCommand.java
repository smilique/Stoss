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

import static com.epam.training.tasks.stoss.entities.Attributes.*;
import static com.epam.training.tasks.stoss.entities.Pages.*;

public class SetLocaleCommand implements Command{

    private static final Logger LOGGER = Logger.getLogger(SetLocaleCommand.class);

    private static final String INDEX_PAGE = "controller?command=index";

    private static final String MAIN_CONTROLLER_PAGE = "controller?command=mainPage";
    private static final String MESSAGE_BOARD_CONTROLLER_PAGE = "controller?command=chat";
    private static final String RATING_CONTROLLER_PAGE = "controller?command=rating";
    private static final String USERS_CONTROLLER_PAGE = "controller?command=users";
    private static final String NEWS_CONTROLLER_PAGE = "controller?command=news&page=1&items=2";

    private final UserService userService;


    public SetLocaleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String languageTag = request.getParameter(LANGUAGE_TAG_ATTRIBUTE);
        Locale locale = Locale.forLanguageTag(languageTag);
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE_ATTRIBUTE, locale);
        response.setLocale(locale);
        String page = INDEX_PAGE;

        if (session.getAttribute(USER_ATTRIBUTE) != null) {
            User user = (User) session.getAttribute(USER_ATTRIBUTE);
                Optional<User> updatedUser = userService.changeLocale(user.getId(), languageTag);
                session.setAttribute(USER_ATTRIBUTE, updatedUser.get());
                String currentPage = (String) session.getAttribute(CURRENT_PAGE_ATTRIBUTE);
                switch (currentPage) {
                    case MAIN_PAGE: {
                        page = MAIN_CONTROLLER_PAGE;
                        break;
                    }
                    case MESSAGE_BOARD_PAGE: {
                        page = MESSAGE_BOARD_CONTROLLER_PAGE;
                        break;
                    }
                    case RATING_PAGE: {
                        page = RATING_CONTROLLER_PAGE;
                        break;
                    }
                    case USERS_PAGE: {
                        page = USERS_CONTROLLER_PAGE;
                        break;
                    }
                    case NEWS_PAGE: {
                        page = NEWS_CONTROLLER_PAGE;
                        break;
                    }
                    default: {
                        page = currentPage;
                        break;
                    }
                }
        }

        return CommandResult.forward(page);
    }
}
