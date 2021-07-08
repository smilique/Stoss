package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.services.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface represents a pattern for processing {@link HttpServletRequest}
 * and {@link HttpServletResponse}
 *
 * @author Anton Tomashevich
 * @see com.epam.training.tasks.stoss.commands.CommandFactory
 * @see com.epam.training.tasks.stoss.commands.CommandResult
 */
public interface Command {


    /**
     * The method generates CommandResult as a result of request and response processing
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return CommandResult {@link CommandResult}
     * @throws ServiceException {@link ServiceException}
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
