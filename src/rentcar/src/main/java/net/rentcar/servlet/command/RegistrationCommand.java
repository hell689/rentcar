package net.rentcar.servlet.command;

import domain.Role;
import domain.User;
import service.UserService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Objects;

public class RegistrationCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String errorMessage = null;
        if (Objects.nonNull(login) || Objects.nonNull(password) || Objects.nonNull(name) ||
                Objects.nonNull(surname) || Objects.nonNull(address)) {
            UserService userService = (UserService) ctx.getBean("userService");
            User user = userService.getByLogin(login);
            if (user == null) {
                user = new User();
                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setPassword(password);
                user.setAddress(address);
                user.setRole(Role.CLIENT);
                try {
                    userService.saveUser(user);
                    errorMessage = "Вы успешно зарегистрировались, можете войти под своим логином/паролем";
                    request.setAttribute("errorMessage", errorMessage);
                    forward("index");
                } catch (ServiceException e) {
                    errorMessage = "Ошибка сохранения данных пользователя";
                    request.setAttribute("errorMessage", errorMessage);
                    forward("registration");
                }
            } else {
                errorMessage = "Пользователь с таким логином уже существует";
                request.setAttribute("errorMessage", errorMessage);
                forward("registration");
            }
        }
        forward("registration");
    }
}
