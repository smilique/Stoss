package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.services.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMessageCommand implements Command {

    public DeleteMessageCommand(MessageService messageService) {

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        
        return null;
    }
}
