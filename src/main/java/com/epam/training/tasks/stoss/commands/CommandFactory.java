package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.services.MessageService;
import com.epam.training.tasks.stoss.services.NewsService;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;


public class CommandFactory {

    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    private static final String LOGIN_COMMAND = "login";
    private static final String MAIN_PAGE_COMMAND = "mainPage";
    private static final String CHAT_PAGE_COMMAND = "chat";
    private static final String CHAT_POST_COMMAND = "sendMessage";
    private static final String CHAT_DELETE_COMMAND = "deleteMessage";
    private static final String INDEX_PAGE_COMMAND = "index";
    private static final String USER_PAGE_COMMAND = "user";
    private static final String USERS_PAGE_COMMAND = "users";
    private static final String USER_UPDATE_COMMAND = "updateUser";
    private static final String USER_DELETE_COMMAND = "deleteUser";
    private static final String USERPIC_UPDATE_COMMAND = "updateUserpic";
    private static final String DEPOSIT_COMMAND = "deposit";
    private static final String REGISTER_PAGE_COMMAND = "registerPage";
    private static final String REGISTER_COMMAND = "register";
    private static final String NEWS_PAGE_COMMAND = "news";
    private static final String RULES_PAGE_COMMAND = "rules";
    private static final String RATING_PAGE_COMMAND = "rating";
    private static final String ADMINISTRATION_PAGE_COMMAND = "userAdministration";
    private static final String START_GAME_COMMAND = "startGame";
    private static final String CHOOSE_CARD_COMMAND = "chooseCard";
    private static final String BET_COMMAND = "bet";
    private static final String CHANGE_LOCALE_COMMAND = "changeLocale";
    private static final String LOGOUT_COMMAND = "logout";
    private static final String DELETE_USER_CONFIRM_COMMAND = "confirmUserDelete";

    private static final String INDEX_PAGE = "/index.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String REGISTER_PAGE = "WEB-INF/view/register.jsp";
    private static final String USER_PAGE = "WEB-INF/view/user.jsp";
    private static final String USER_DELETE_CONFIRM_PAGE = "WEB-INF/view/userDeleteConfirm.jsp";
    private static final String NOT_IMPLEMENTED_PAGE = "WEB-INF/view/notImplemented.jsp";
    private static final String RULES_PAGE = "WEB-INF/view/rules.jsp";
    private static final String RATING_PAGE = "WEB-INF/view/rating.jsp";
    private static final String ADMINISTRATION = "WEB-INF/view/admin.jsp";

    public Command create(String type) {
        LOGGER.debug(" type = " + type);
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
            case CHAT_PAGE_COMMAND: {
                return new ShowChatCommand(new MessageService(new DaoHelperFactory()));
            }
            case CHAT_POST_COMMAND: {
                return new SendMessageCommand(new MessageService(new DaoHelperFactory()));
            }
            case REGISTER_COMMAND: {
                return new RegisterCommand(new UserService());
            }
            case RULES_PAGE_COMMAND:
            case ADMINISTRATION_PAGE_COMMAND:
            case RATING_PAGE_COMMAND: {
                return new ShowPageCommand(NOT_IMPLEMENTED_PAGE);
            }
            case USER_PAGE_COMMAND: {
                return new ShowPageCommand(USER_PAGE);
            }
            case USER_UPDATE_COMMAND: {
                return new UpdateCommand(new UserService());
            }
            case USER_DELETE_COMMAND: {
                return new DeleteUserCommand(new UserService());
            }
            case DELETE_USER_CONFIRM_COMMAND: {
                return new DeleteUserConfirmCommand();
            }
            case USER_DELETE_CONFIRM_PAGE: {
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
            case NEWS_PAGE_COMMAND: {
                return new ShowNewsCommand(new NewsService(new DaoHelperFactory()));
            }

            case CHANGE_LOCALE_COMMAND: {
                return new SetLocaleCommand(new UserService());
            }

            case LOGOUT_COMMAND: {
                return new LogoutCommand();
            }
            default: {
                LOGGER.error(" unexpected command!");
                throw new IllegalArgumentException("Unknown command: " + type);
            }
        }
    }
}
