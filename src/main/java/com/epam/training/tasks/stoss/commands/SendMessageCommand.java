package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.dao.DaoHelperFactory;
import com.epam.training.tasks.stoss.entities.Message;
import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.MessageService;
import com.epam.training.tasks.stoss.services.NewsService;
import com.epam.training.tasks.stoss.services.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SendMessageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SendMessageCommand.class);

    private static final String CHAT_PAGE = "controller?command=chat";

    private final MessageService messageService;

    public SendMessageCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String message = request.getParameter("text");
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");
            Long userId = currentUser.getId();
            LOGGER.debug("user id: " + userId);
            LOGGER.debug("message: " + message);
            messageService.postMessage(message,userId);

            List<Message> messages = messageService.getMessages();
            request.setAttribute("messages", messages);
        } catch (ServiceException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }

        return CommandResult.redirect(CHAT_PAGE);
    }
}
