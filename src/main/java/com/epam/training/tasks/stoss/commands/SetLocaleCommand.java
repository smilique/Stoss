package com.epam.training.tasks.stoss.commands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class SetLocaleCommand implements Command{
    private static final Logger LOGGER = Logger.getLogger(SetLocaleCommand.class);

    private final static String LOCALE_ATTRIBUTE = "locale";

    private final Locale locale;

    public SetLocaleCommand (Locale locale) {
        this.locale = locale;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE_ATTRIBUTE, locale);
        response.setLocale(locale);
//        String page = request.getRequestURI();
//        StringBuffer requestBuffer = request.getRequestURL();
////        LOGGER.debug(requestBuffer.toString());
////        LOGGER.debug("page: " + page);

        return CommandResult.forward("controller?command=index");
    }
}
