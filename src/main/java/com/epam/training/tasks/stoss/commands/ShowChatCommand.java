package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.Message;
import com.epam.training.tasks.stoss.services.MessageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class ShowChatCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowChatCommand.class);

    private static final String CHAT_PAGE = "WEB-INF/view/chat.jsp";

    private final MessageService messageService;

    public ShowChatCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Message> messages = messageService.getMessages();
            Collections.reverse(messages);
            request.setAttribute("messages", messages);
        } catch (Exception e) {
            LOGGER.error(e);
            e.printStackTrace();
        }

        return CommandResult.forward(CHAT_PAGE);
    }
}
