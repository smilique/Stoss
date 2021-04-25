package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import java.util.Locale;


public class CommandFactory {

    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    private static final String LOGIN_COMMAND = "login";
    private static final String MAIN_PAGE_COMMAND = "mainPage";
    private static final String INDEX_PAGE_COMMAND = "index";
    private static final String USER_PAGE_COMMAND = "user";
    private static final String USER_UPDATE_COMMAND = "updateUser";
    private static final String USERPIC_UPDATE_COMMAND = "updateUserpic";
    private static final String DEPOSIT_COMMAND = "deposit";
    private static final String REGISTER_PAGE_COMMAND = "registerPage";
    private static final String REGISTER_COMMAND = "register";
    private static final String NEWS_PAGE_COMMAND = "news";
    private static final String LOCALE_RU_COMMAND = "localeRu";
    private static final String LOCALE_BY_COMMAND = "localeBy";
    private static final String LOCALE_EN_COMMAND = "localeEn";
    private static final String LOGOUT_COMMAND = "logout";

    private static final String INDEX_PAGE = "/index.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String REGISTER_PAGE = "WEB-INF/view/register.jsp";
    private static final String USER_PAGE = "WEB-INF/view/user.jsp";
    private static final String NEWS_PAGE = "WEB-INF/view/news.jsp";

    private static final String EN_LANGUAGE_TAG = "en";
    private static final String RU_LANGUAGE_TAG = "ru";
    private static final String BY_LANGUAGE_TAG = "by";

    public Command create(String type) {
        LOGGER.debug(" type = " + type);
        switch (type) {
            case LOGIN_COMMAND: {
                LOGGER.debug(" login command sent");
                return new LoginCommand(new UserService());
            }
            case MAIN_PAGE_COMMAND: {
                LOGGER.debug(" main page sent");
                return new ShowPageCommand(MAIN_PAGE);
            }
            case INDEX_PAGE_COMMAND: {
                LOGGER.debug(" index page sent");
                return new ShowPageCommand(INDEX_PAGE);
            }
            case REGISTER_PAGE_COMMAND: {
                LOGGER.debug(" register page sent");
                return new ShowPageCommand(REGISTER_PAGE);
            }
            case REGISTER_COMMAND: {
                return new RegisterCommand(new UserService());
            }
            case USER_PAGE_COMMAND: {
                return new ShowPageCommand(USER_PAGE);
            }
            case USER_UPDATE_COMMAND: {
                return new UpdateCommand(new UserService());//TODO
            }
            case USERPIC_UPDATE_COMMAND: {
                return new UserpicUpdateCommand(new UserService());
            }
            case DEPOSIT_COMMAND: {
                return new DepositCommand(new UserService());
            }
            case NEWS_PAGE_COMMAND: {
                return new ShowPageCommand(NEWS_PAGE);
            }

            case LOCALE_RU_COMMAND: {
                LOGGER.debug("locale changed to ru");
                return new SetLocaleCommand(Locale.forLanguageTag(RU_LANGUAGE_TAG));
            }
            case LOCALE_BY_COMMAND: {
                LOGGER.debug("locale changed to by");
                return new SetLocaleCommand(Locale.forLanguageTag(BY_LANGUAGE_TAG));
            }
            case LOCALE_EN_COMMAND: {
                LOGGER.debug("locale changed to en");
                return new SetLocaleCommand(Locale.forLanguageTag(EN_LANGUAGE_TAG));
            }
            case LOGOUT_COMMAND: {
                LOGGER.debug("logout command sent");
                return new LogoutCommand();
            }
            default: {
                LOGGER.error(" unexpected command!");
                throw new IllegalArgumentException("Unknown command: " + type);
            }
        }
    }
}
