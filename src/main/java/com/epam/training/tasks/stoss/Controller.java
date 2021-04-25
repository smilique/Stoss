package com.epam.training.tasks.stoss;

import com.epam.training.tasks.stoss.commands.Command;
import com.epam.training.tasks.stoss.commands.CommandFactory;
import com.epam.training.tasks.stoss.commands.CommandResult;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(Controller.class);

    private final CommandFactory factory = new CommandFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandType = request.getParameter("command");
        Command command = factory.create(commandType);
        LOGGER.debug( " COMMAND: " + commandType);
        String page;
        boolean isRedirect = false;

        try {
            CommandResult result = command.execute(request, response);
            LOGGER.debug("CommandResult " + result);
            page = result.getPage();
            LOGGER.debug(" Page: " + page);
            isRedirect = result.isRedirect();
        } catch (Exception e) {
            request.setAttribute("errormessage", e.getMessage());
            page = "/error.jsp"; //?
            LOGGER.error("Exception: " + e);
//            e.printStackTrace();
        }
        if (!isRedirect) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(page); //?
        }

    }
}