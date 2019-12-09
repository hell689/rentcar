package net.rentcar.servlet.command;

import domain.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String errorMessage = null;
        if (login != null && password != null) {
            UserService userService = (UserService) ctx.getBean("userService");
            User user = userService.getByLoginAndPassword(login, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);
            } else {
                errorMessage = "Пользователь с таким логином/паролем не зарегистрирован";
                request.setAttribute("errorMessage", errorMessage);
            }
        }
        forward("index");
    }
}
