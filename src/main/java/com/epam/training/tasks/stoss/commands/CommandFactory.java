package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.services.MessageService;
import com.epam.training.tasks.stoss.services.NewsService;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import static com.epam.training.tasks.stoss.commands.Commands.*;
import static com.epam.training.tasks.stoss.commands.Pages.*;

public class CommandFactory {

    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    public Command create(String type) {
        LOGGER.debug("Command: " + type);
        switch (type) {
            case LOGIN_COMMAND: {
                return new LoginCommand(new UserService());
            }
            case MAIN_PAGE_COMMAND: {
                return new ShowPageCommand(MAIN_PAGE);
            }
            case INDEX_PAGE_COMMAND: {
                return new ShowPageCommand(INDEX_PAGE);
            }
            case RULES_PAGE_COMMAND: {
                return new ShowPageCommand(RULES_PAGE);
            }
            case REGISTER_PAGE_COMMAND: {
                return new ShowPageCommand(REGISTER_PAGE);
            }
            case START_GAME_COMMAND: {
                return new StartGameCommand();
            }
            case CHOOSE_CARD_COMMAND: {
                return new ChooseCardCommand();
            }
            case BET_COMMAND: {
                return new BetCommand(new UserService());
            }
            case MESSAGE_BOARD_PAGE_COMMAND: {
                return new ShowMessageBoardCommand(new MessageService());
            }
            case MESSAGE_POST_COMMAND: {
                return new SendMessageCommand(new MessageService());
            }
            case MESSAGE_DELETE_COMMAND: {
                return new DeleteMessageCommand(new MessageService());
            }
            case REGISTER_COMMAND: {
                return new RegisterCommand(new UserService());
            }
            case RATING_PAGE_COMMAND: {
                return new ShowRatingCommand(new UserService());
            }
            case USER_PAGE_COMMAND: {
                return new ShowPageCommand(USER_PAGE);
            }
            case BALANCE_PAGE_COMMAND: {
                return new ShowPageCommand(BALANCE_PAGE);
            }
            case EDIT_USER_COMMAND: {
                return new EditAnotherUserCommand(new UserService());
            }
            case USER_UPDATE_COMMAND: {
                return new UpdateCommand(new UserService());
            }
            case USER_SPECIFIED_UPDATE_COMMAND: {
                return new UpdateSpecifiedUserCommand(new UserService());
            }
            case USER_DELETE_COMMAND: {
                return new DeleteUserCommand(new UserService());
            }
            case USER_DELETE_CONFIRM_COMMAND: {
                return new ShowPageCommand(USER_DELETE_CONFIRM_PAGE);
            }
            case USERS_PAGE_COMMAND: {
                return new ShowUsersCommand(new UserService());
            }
            case USERPIC_UPDATE_COMMAND: {
                return new UserpicUpdateCommand(new UserService());
            }
            case DEPOSIT_COMMAND: {
                return new DepositCommand(new UserService());
            }
            case WITHDRAW_COMMAND: {
                return new WithdrawCommand(new UserService());
            }
            case NEWS_PAGE_COMMAND: {
                return new ShowNewsCommand(new NewsService(new DaoHelperFactory()));
            }
            case NEWS_ITEM_DELETE_COMMAND: {
                return new DeleteNewsItemCommand(new NewsService(new DaoHelperFactory()));
            }
            case CHANGE_LOCALE_COMMAND: {
                return new SetLocaleCommand(new UserService());
            }
            case LOGOUT_COMMAND: {
                return new LogoutCommand();
            }
            default: {
                LOGGER.error("Unexpected command: " + type);
                throw new IllegalArgumentException("Unknown command: " + type);
            }
        }
    }
}
