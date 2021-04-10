package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import java.util.Locale;


public class CommandFactory {

    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    private final static String LOGIN_TYPE = "login";
    private static final String MAIN_PAGE_TYPE = "mainPage";
    private static final String INDEX_TYPE = "index";
    private static final String REGISTER_PAGE_TYPE = "registerPage";
    private static final String LOCALE_RU_TYPE = "ru";
    private static final String LOCALE_BY_TYPE = "by";
    private static final String LOCALE_EN_TYPE = "en";

    private static final String INDEX_PAGE = "/index.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String REGISTER_PAGE = "WEB-INF/view/register.jsp";

    private static final String EN_LANGUAGE_TAG = "en";
    private static final String RU_LANGUAGE_TAG = "ru";
    private static final String BY_LANGUAGE_TAG = "by";

    public Command create(String type) {
        LOGGER.debug(" type = " + type);
        switch (type) {
            case LOGIN_TYPE: {
                LOGGER.debug(" login command sent");
                return new LoginCommand(new UserService());
            }
            case MAIN_PAGE_TYPE: {
                LOGGER.debug(" main page sent");
                return new ShowPageCommand(MAIN_PAGE);
            }
            case INDEX_TYPE: {
                LOGGER.debug(" index page sent");
                return new ShowPageCommand(INDEX_PAGE);
            }
            case REGISTER_PAGE_TYPE: {
                LOGGER.debug(" register page sent");
                return new ShowPageCommand(REGISTER_PAGE);
            }
            case LOCALE_RU_TYPE: {
                LOGGER.debug("locale changed to ru");
                return new SetLocaleCommand(Locale.forLanguageTag(RU_LANGUAGE_TAG));
            }
            case LOCALE_BY_TYPE: {
                LOGGER.debug("locale changed to by");
                return new SetLocaleCommand(Locale.forLanguageTag(BY_LANGUAGE_TAG));
            }
            case LOCALE_EN_TYPE: {
                LOGGER.debug("locale changed to en");
                return new SetLocaleCommand(Locale.forLanguageTag(EN_LANGUAGE_TAG));
            }
            default: {
                LOGGER.error(" unexpected command!");
                throw new IllegalArgumentException("Unknown command: " + type);
            }
        }
    }
}
