package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.entities.Message;
import com.epam.training.tasks.stoss.services.MessageService;
import com.epam.training.tasks.stoss.services.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.training.tasks.stoss.entities.Attributes.MESSAGES_ATTRIBUTE;
import static com.epam.training.tasks.stoss.entities.Pages.MESSAGE_BOARD_PAGE;

public class ShowMessageBoardCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowMessageBoardCommand.class);

    private final MessageService messageService;

    public ShowMessageBoardCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        List<Message> messages = messageService.getMessages();
        request.setAttribute(MESSAGES_ATTRIBUTE, messages);

        return CommandResult.forward(MESSAGE_BOARD_PAGE);
    }
}
