package com.epam.training.tasks.stoss.filters;

import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.training.tasks.stoss.entities.Attributes.*;
import static com.epam.training.tasks.stoss.entities.Commands.*;
import static com.epam.training.tasks.stoss.entities.Pages.INDEX_PAGE;

public class AdminFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AdminFilter.class);

    private static final String ADMIN_ROLE = "admin";
//    private static final String USERS_PAGE = "WEB-INF/view/users.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Filter started");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String command = request.getParameter(COMMAND_ATTRIBUTE);
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (user != null && !user.getRole().equals(ADMIN_ROLE)) {
            if (command.equals(USER_DELETE_COMMAND) ||
                    command.equals(MESSAGE_DELETE_COMMAND) ||
                    command.equals(USER_DELETE_CONFIRM_COMMAND)) { //TODO confirmUserDelete editUser
                LOGGER.info("yo");
                servletRequest.setAttribute(ERROR_MESSAGE_ATTRIBUTE,"You have no rights");
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(INDEX_PAGE);
                dispatcher.forward(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
