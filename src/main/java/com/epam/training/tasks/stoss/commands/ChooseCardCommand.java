package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.Card;
import com.epam.training.tasks.stoss.entities.Deck;
import com.epam.training.tasks.stoss.entities.Game;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.training.tasks.stoss.entities.Attributes.*;
import static com.epam.training.tasks.stoss.entities.Pages.BET_PAGE;

public class ChooseCardCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChooseCardCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute(GAME_ATTRIBUTE);
        Deck punterCards = game.getPunterDeck();
        String punterCardCode = request.getParameter(CARD_ATTRIBUTE);
        Card punterCard = punterCards.getByCode(punterCardCode);
        session.setAttribute(PUNTER_CARD_ATTRIBUTE, punterCard);
        LOGGER.info("Punter's card: " + punterCard);
        return CommandResult.forward(BET_PAGE);
    }
}
