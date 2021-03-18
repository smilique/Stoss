package com.epam.training.tasks.stoss.commands;


import com.epam.training.tasks.stoss.entities.User;
import com.epam.training.tasks.stoss.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userService.login(username, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            request.setAttribute("name", user.getName());
        } else {
            //sth new
            request.setAttribute("errormessage", "Invalid username or password");
        }

        return "WEB-INF/view/main.jsp";
    }
}
