package com.epam.training.tasks.stoss.filters;

import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.training.tasks.stoss.commands.Attributes.COMMAND_ATTRIBUTE;
import static com.epam.training.tasks.stoss.commands.Attributes.USER_ATTRIBUTE;
import static com.epam.training.tasks.stoss.commands.Commands.*;
import static com.epam.training.tasks.stoss.commands.Pages.INDEX_PAGE;

public class AuthorizationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter(COMMAND_ATTRIBUTE);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (user == null) {
            LOGGER.info("No user detected");
            if (command.equals(LOGIN_COMMAND) ||
                    command.equals(REGISTER_PAGE_COMMAND) ||
                    command.equals(REGISTER_COMMAND) ||
                    command.equals(CHANGE_LOCALE_COMMAND)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(INDEX_PAGE);
                dispatcher.forward(servletRequest, servletResponse);
            }
        } else {
            LOGGER.info("User detected");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
