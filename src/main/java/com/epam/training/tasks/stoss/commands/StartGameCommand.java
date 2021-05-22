package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.Game;
import com.epam.training.tasks.stoss.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.training.tasks.stoss.entities.Attributes.GAME_ATTRIBUTE;
import static com.epam.training.tasks.stoss.entities.Attributes.USER_ATTRIBUTE;
import static com.epam.training.tasks.stoss.entities.Pages.GAME_PAGE;

public class StartGameCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(StartGameCommand.class);

//    private static final String PAGE = "WEB-INF/view/game.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Starting the game");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(USER_ATTRIBUTE);
        Game currentGame = new Game(currentUser.getId());
        session.setAttribute(GAME_ATTRIBUTE, currentGame);

        return CommandResult.forward(GAME_PAGE);
    }
}
