package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.Card;
import com.epam.training.tasks.stoss.entities.Deck;
import com.epam.training.tasks.stoss.entities.Game;
import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;


public class BetCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(BetCommand.class);

    private static final String PAGE = "WEB-INF/view/gameresult.jsp";
    private static final String WIN_STATUS = "win";
    private static final String LOSE_STATUS = "lose";
    private static final String CONTINUE_STATUS = "continue";
    private static final String BET_VALUE_PARAMETER = "betValue";
    private static final String GAME_ATTRIBUTE = "game";
    private static final String GAME_STATUS_ATTRIBUTE = "gameStatus";
    private static final String PUNTER_CARD_ATTRIBUTE = "punterCard";
    private static final String FIRST_CARD_ATTRIBUTE = "firstCard";
    private static final String SECOND_CARD_ATTRIBUTE = "secondCard";
    private static final String POINTS_CHANGE_ATTRIBUTE = "pointsChange";
    private static final Long POINTS_MULTIPLIER = 3L;
//    private static final Long LOSE_POINTS_MULTIPLIER = -3L;
    private static final Long NEGATE = -1L;


    private final UserService userService;
    public BetCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LOGGER.debug(session);
        Game game = (Game) session.getAttribute(GAME_ATTRIBUTE);
        LOGGER.debug(game);
        String betString = request.getParameter(BET_VALUE_PARAMETER);
        LOGGER.debug(betString);
        Long betValue = Long.valueOf(betString);
        if (betValue != null) {
            game.setBet(betValue);
        } else {
            betValue = game.getBet();
        }
        LOGGER.debug("betValue: " + betValue);
        Deck bankerDeck = game.getBankerDeck();
        Card firstCard = bankerDeck.getCard();
        Card secondCard = bankerDeck.getCard();
        Card punterCard = (Card) session.getAttribute(PUNTER_CARD_ATTRIBUTE);
        String firstCardName = firstCard.getName();
        String punterCardName = punterCard.getName();
        String secondCardName = secondCard.getName();
        request.setAttribute(FIRST_CARD_ATTRIBUTE, firstCard);
        request.setAttribute(SECOND_CARD_ATTRIBUTE, secondCard);
        request.setAttribute(PUNTER_CARD_ATTRIBUTE, punterCard);
        if (punterCardName.equals(firstCardName)) {
            //punter loses, banker wins
            LOGGER.debug("LOSE");
            betValue *= NEGATE;
            process(request, session, betValue, LOSE_STATUS);


        } else if (punterCardName.equals(secondCardName)) {
            //punter wins, banker loses
            LOGGER.debug("WIN");
            process(request, session, betValue, WIN_STATUS);
        } else {
            //next round
            LOGGER.debug("continue");
            request.setAttribute("betValue", betValue);
            game.setBankerDeck(bankerDeck);
            session.setAttribute(GAME_ATTRIBUTE, game);
            request.setAttribute(GAME_STATUS_ATTRIBUTE,CONTINUE_STATUS);
        }

        return CommandResult.forward(PAGE);
    }

    private void process(HttpServletRequest request, HttpSession session, Long betValue, String winStatus) {
        session.removeAttribute(GAME_ATTRIBUTE);
        session.removeAttribute(PUNTER_CARD_ATTRIBUTE);
        request.setAttribute (GAME_STATUS_ATTRIBUTE, winStatus);
        User currentUser = (User) session.getAttribute("user");
        Long pointsChange = betValue * POINTS_MULTIPLIER;
        request.setAttribute(POINTS_CHANGE_ATTRIBUTE, pointsChange);
        Long points = currentUser.getPoints() + pointsChange;
        Long userId = currentUser.getId();
        BigDecimal bet = BigDecimal.valueOf(betValue);
        try {
            userService.updatePoints(userId, points);
            Optional<User> optionalUser = userService.deposit(userId, bet);
            User user = optionalUser.get();
            session.setAttribute("user", user);
        } catch (ServiceException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
    }


}
