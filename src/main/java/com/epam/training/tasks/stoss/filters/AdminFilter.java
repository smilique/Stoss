package com.epam.training.tasks.stoss.filters;

import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.training.tasks.stoss.entities.Attributes.*;
import static com.epam.training.tasks.stoss.entities.Commands.*;
import static com.epam.training.tasks.stoss.entities.Pages.ERROR_PAGE;

public class AdminFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AdminFilter.class);

    private static final String ADMIN_ROLE = "admin";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Admin filter started");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String command = request.getParameter(COMMAND_ATTRIBUTE);
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (user != null && !user.getRole().equals(ADMIN_ROLE)) {
            LOGGER.info("User without admin rights");
            if (command.equals(USER_DELETE_COMMAND) ||
                    command.equals(MESSAGE_DELETE_COMMAND) ||
                    command.equals(USER_DELETE_CONFIRM_COMMAND) ||
                    command.equals(USERS_PAGE_COMMAND)) {
                LOGGER.info("Running admin only command");
                servletRequest.setAttribute(ERROR_MESSAGE_ATTRIBUTE,"You have no rights to run this command");
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(ERROR_PAGE);
                dispatcher.forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
