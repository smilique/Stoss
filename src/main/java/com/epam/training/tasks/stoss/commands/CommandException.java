package com.epam.training.tasks.stoss.commands;

public class CommandException extends Exception {
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message) {
        super(message);
    }
}
