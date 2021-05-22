package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

import static com.epam.training.tasks.stoss.entities.Attributes.*;

public class DepositCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DepositCommand.class);

    private final UserService userService;
    private static final String PAGE = "controller?command=user";

    public DepositCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("executing deposit command");
        String depositParameter = request.getParameter(DEPOSIT_ATTRIBUTE);
        HttpSession session = request.getSession();

        if (!depositParameter.equals("")) {
            BigDecimal deposit = new BigDecimal(depositParameter);
            LOGGER.debug("deposit: " + deposit);
            User user = (User) session.getAttribute(USER_ATTRIBUTE);
            Long userId = user.getId();
            LOGGER.debug("user.id: " + userId);
            Optional<User> updatedUser = null;
            try {
                updatedUser = userService.deposit(userId, deposit);
                User currentUser = updatedUser.get();
                session.setAttribute(USER_ATTRIBUTE, currentUser);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        } else {
            session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Enter amount larger than 0!");
        }

        return CommandResult.redirect(PAGE);
    }
}
