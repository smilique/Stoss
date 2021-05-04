package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.Game;
import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartGameCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(StartGameCommand.class);

    private static final String PAGE = "WEB-INF/view/game.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        Game currentGame = new Game(currentUser.getId());
        session.setAttribute("game", currentGame);

        return CommandResult.forward(PAGE);
    }
}
