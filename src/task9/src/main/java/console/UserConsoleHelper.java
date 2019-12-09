package console;

import domain.Role;
import domain.User;
import service.UserService;
import service.exceptions.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;

public class UserConsoleHelper implements ConsoleHelper {

    private UserService userService;
    private BufferedReader br;

    public UserConsoleHelper(UserService userService, BufferedReader br) {
        this.userService = userService;
        this.br = br;
    }

    @Override
    public void addNew() throws IOException {
        User user = new User();
        createUser(user);
        try {
            userService.saveUser(user);
            System.out.println("Пользователь " + user.getLogin() + " зарегистрирован!");
        } catch (ServiceException e) {
            System.out.println("Пользователь с таким логином (" + user.getLogin() + ") уже зарегистрирован в системе!");
        }

    }

    @Override
    public void delete() throws IOException, ServiceException {
        System.out.println("Введите номер удаляемого пользователя: ");
        userService.deleteUser(Long.parseLong(br.readLine()));
    }

    @Override
    public void update() throws IOException {
        User user = new User();
        System.out.println("Введите номер изменяемого пользователя: ");
        user.setId(Long.parseLong(br.readLine()));
        createUser(user);
        try {
            userService.saveUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printList() {
        System.out.println("Users:");
        userService.getUsers().forEach(user -> System.out.println(user.getId() + ": " + user.getName() + " " +
                user.getSurname() + ", адрес: " + user.getAddress() + ", login: " + user.getLogin() + ", role: " + user.getRole()));
    }

    public User UserLogIn() throws IOException {
        System.out.println("Введите логин (напр. user1 или admin): ");
        String login = br.readLine();
        System.out.println("Введите пароль (123):");
        String password = br.readLine();
        User user = userService.getByLoginAndPassword(login, password);
        if (user == null) {
            System.out.println("Пользователь с указанным логином или паролем не найден!\n");
        }
        return user;
    }

    private void createUser(User user) throws IOException {
        System.out.println("Введите имя: ");
        user.setName(br.readLine());
        System.out.println("Введите фамилию: ");
        user.setSurname(br.readLine());
        System.out.println("Введите login: ");
        user.setLogin(br.readLine());
        System.out.println("Введите password: ");
        user.setPassword(br.readLine());
        System.out.println("Введите адрес: ");
        user.setAddress(br.readLine());
        user.setRole(Role.CLIENT);
    }

}
