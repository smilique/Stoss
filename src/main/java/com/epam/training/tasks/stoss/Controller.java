package com.epam.training.tasks.stoss;

import com.epam.training.tasks.stoss.commands.Command;
import com.epam.training.tasks.stoss.commands.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final CommandFactory factory= new CommandFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.getRequestDispatcher("init.jsp").forward(request,response);
        String commandType = request.getParameter("command");

        Command command = factory.create(commandType);

        String view = command.execute(request, response);

        request.getRequestDispatcher(view).forward(request,response);
    }
}