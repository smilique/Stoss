package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.ServiceException;
import com.epam.training.tasks.stoss.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

import static com.epam.training.tasks.stoss.commands.Attributes.*;

public class WithdrawCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(WithdrawCommand.class);

    private final UserService userService;
    private static final String PAGE = "controller?command=balance";

    public WithdrawCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.debug("executing withdraw command");
        String withdrawParameter = request.getParameter(WITHDRAW_ATTRIBUTE);
        HttpSession session = request.getSession();

        if (!withdrawParameter.equals("")) {
            User user = (User) session.getAttribute(USER_ATTRIBUTE);
            Long userId = user.getId();
            BigDecimal userBalance = user.getBalance();
            BigDecimal withdraw = new BigDecimal(withdrawParameter);

            if (userBalance.compareTo(withdraw) >= 0) {
                withdraw = withdraw.negate();
                Optional<User> updatedUser;
                updatedUser = userService.deposit(userId, withdraw);
                User currentUser = updatedUser.get();
                session.setAttribute(USER_ATTRIBUTE, currentUser);
                session.removeAttribute(ERROR_MESSAGE_ATTRIBUTE);
            } else {
                session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Insufficient funds");
            }

        } else {
            session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Enter amount larger than 0!");
        }

        return CommandResult.redirect(PAGE);
    }
}
