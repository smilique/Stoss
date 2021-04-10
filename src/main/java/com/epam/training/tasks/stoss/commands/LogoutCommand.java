package com.epam.training.tasks.stoss.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command{

    private final static String PAGE = "controller?command=index";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();

        return CommandResult.forward(PAGE);
    }
}

