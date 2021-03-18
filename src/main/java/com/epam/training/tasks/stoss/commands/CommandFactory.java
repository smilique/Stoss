package com.epam.training.tasks.stoss.commands;

import com.epam.training.tasks.stoss.services.UserService;

public class CommandFactory {

    public Command create(String type) {
        switch (type) {
            case "login": {
                return new LoginCommand(new UserService());
            }
            default: {
                throw new IllegalArgumentException("Unknown type of Command" + type);
            }
        }
    }
}
