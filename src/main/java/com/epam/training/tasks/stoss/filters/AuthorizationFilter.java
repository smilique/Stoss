package com.epam.training.tasks.stoss.filters;

import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);

    private static final String COMMAND_PARAMETER = "command";
    private static final String USER_ATTRIBUTE = "user";
    private static final String LOGIN_COMMAND = "login";
    private static final String INDEX_PAGE = "index.jsp";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter(COMMAND_PARAMETER);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (!command.equals(LOGIN_COMMAND) && user == null) {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(INDEX_PAGE);
            dispatcher.forward(servletRequest, servletResponse);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
