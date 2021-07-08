package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.services.MessageService;
import com.epam.training.tasks.stoss.services.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMessageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteMessageCommand.class);
    private static final String PAGE = "controller?command=chat";

    private final MessageService messageService;

    public DeleteMessageCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String messageId = request.getParameter("messageId");
        Long id = Long.valueOf(messageId);
        LOGGER.debug("Deleting message ID:" + messageId);
        messageService.deleteMessage(id);
        return CommandResult.forward(PAGE);
    }
}
