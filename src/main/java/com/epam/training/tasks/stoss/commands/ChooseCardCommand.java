package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.Card;
import com.epam.training.tasks.stoss.entities.Deck;
import com.epam.training.tasks.stoss.entities.Game;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChooseCardCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChooseCardCommand.class);

    private static final String PAGE = "WEB-INF/view/bet.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        Deck punterCards = game.getPunterDeck();
        String punterCardCode = request.getParameter("card");
        Card punterCard = punterCards.getByCode(punterCardCode);
        session.setAttribute("punterCard", punterCard);

        return CommandResult.forward(PAGE);
    }
}
