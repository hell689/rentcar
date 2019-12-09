/**
 * Task9 class
 *
 * @author    Alexey Vladyko
 *
 * 3. Система Аренда автомобиля. Клиент заполняет заявку, указывая марку, тип коробки передач, желаемый объем двигателя
 * и цвет автомобиля, даты, на которые будет арендован автомобиль, а также комментарий. Администратор просматривает
 * заявки и выделяет наиболее подходящий из доступных автомобилей.
 */

import console.*;
import domain.Role;
import domain.User;
import service.*;
import service.exceptions.ServiceException;
import support.ApplicationContext;
import support.ApplicationContextImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Task9 {
    private static BufferedReader br;
    private static int n;
    private static Screens currentScreen;
    private static User currentUser;

    public static void main(String[] args) {
        ApplicationContext ctx = ApplicationContextImpl.getInstance();
        CarService carService = (CarService) ctx.getBean("carService");
        ColorService colorService = (ColorService) ctx.getBean("colorService");
        MarkService markService = (MarkService) ctx.getBean("markService");
        GearboxService gearboxService = (GearboxService) ctx.getBean("gearboxService");
        UserService userService = (UserService) ctx.getBean("userService");
        RequestService requestService = (RequestService) ctx.getBean("requestService");
        RentService rentService = (RentService) ctx.getBean("rentService");

        br = new BufferedReader(new InputStreamReader(System.in));

        ColorConsoleHelper colorConsoleHelper = new ColorConsoleHelper(colorService, br);
        MarkConsoleHelper markConsoleHelper = new MarkConsoleHelper(markService, br);
        GearboxConsoleHelper gearboxConsoleHelper = new GearboxConsoleHelper(gearboxService, br);
        CarConsoleHelper carConsoleHelper = new CarConsoleHelper(carService, br, colorConsoleHelper, markConsoleHelper, gearboxConsoleHelper);
        UserConsoleHelper userConsoleHelper = new UserConsoleHelper(userService, br);
        RequestConsoleHelper requestConsoleHelper = new RequestConsoleHelper(requestService, br, colorConsoleHelper, markConsoleHelper, gearboxConsoleHelper, currentUser);
        RentConsoleHelper rentConsoleHelper = new RentConsoleHelper(rentService, br, carConsoleHelper, requestConsoleHelper);
        currentScreen = Screens.HOME;

        try {
            while (true) {

                // Авторизация/регистрация
                if (currentUser == null) {
                    System.out.println(Menu.AUTORIZATION_MENU);
                    try {
                        n = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException ignored) {
                    }
                    switch (n) {
                        case 1:
                            currentUser = userConsoleHelper.UserLogIn();
                            break;
                        case 2:
                            userConsoleHelper.addNew();
                            break;
                    }
                    if (n == 0) {
                        break;
                    }
                    continue;
                }

                // Пользователь-администратор
                if (currentUser.getRole() == Role.ADMINISTRATOR) {
                    if (currentScreen.equals(Screens.HOME)) {
                        System.out.println(Menu.SEPARATOR);
                        System.out.println(Menu.HOME_MENU_ADMIN);
                        try {
                            n = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException ignored) {
                        }
                        if (n == 0) {
                            break;
                        }
                        requestConsoleHelper.setUser(currentUser);
                        switch (n) {
                            case 1:
                                colorConsoleHelper.printList();
                                currentScreen = Screens.COLORS;
                                break;
                            case 2:
                                markConsoleHelper.printList();
                                currentScreen = Screens.MARKS;
                                break;
                            case 3:
                                gearboxConsoleHelper.printList();
                                currentScreen = Screens.GEARBOXES;
                                break;
                            case 4:
                                carConsoleHelper.printList();
                                currentScreen = Screens.CARS;
                                break;
                            case 5:
                                userConsoleHelper.printList();
                                currentScreen = Screens.USERS;
                                break;
                            case 6:
                                requestConsoleHelper.printList();
                                currentScreen = Screens.REQESTS;
                                break;
                            case 7:
                                rentConsoleHelper.printList();
                                currentScreen = Screens.RENTS;
                                break;
                            case 9:
                                currentUser = null;
                                break;
                            default:
                                System.err.println("Неверный ввод!");
                        }
                    } else if (currentScreen.equals(Screens.COLORS)) {
                        operationChoise(colorConsoleHelper);
                    } else if (currentScreen.equals(Screens.MARKS)) {
                        operationChoise(markConsoleHelper);
                    } else if (currentScreen.equals(Screens.GEARBOXES)) {
                        operationChoise(gearboxConsoleHelper);
                    } else if (currentScreen.equals(Screens.CARS)) {
                        operationChoise(carConsoleHelper);
                    } else if (currentScreen.equals(Screens.USERS)) {
                        operationChoise(userConsoleHelper);
                    } else if (currentScreen.equals(Screens.REQESTS)) {
                        operationChoise(requestConsoleHelper);
                    } else if (currentScreen.equals(Screens.RENTS)) {
                        operationChoise(rentConsoleHelper);
                    }
                    if (n == 0) {
                        break;
                    }

                    //Пользователь-клиент
                } else if (currentUser.getRole() == Role.CLIENT) {
                    if (currentScreen.equals(Screens.HOME)) {
                        System.out.println(Menu.SEPARATOR);
                        System.out.println(Menu.HOME_MENU_CLIENT);
                        try {
                            n = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException ignored) {
                        }
                        if (n == 0) {
                            break;
                        }
                        switch (n) {
                            case 1:
                                carConsoleHelper.printList();
                                currentScreen = Screens.CARS;
                                break;
                            case 2:
                                requestConsoleHelper.setUser(currentUser);
                                requestConsoleHelper.printList();
                                currentScreen = Screens.REQESTS;
                                break;
                            case 9:
                                currentUser = null;
                                break;
                            default:
                                System.err.println("Неверный ввод!");
                        }
                    } else if (currentScreen.equals(Screens.CARS)) {
                        operationChoise(carConsoleHelper);
                    } else if (currentScreen.equals(Screens.REQESTS)) {
                        operationChoise(requestConsoleHelper);
                    }
                    if (n == 0) {
                        break;
                    }
                }
            }
        } catch (IOException | ParseException | ServiceException e) {
            System.err.println("Неверный ввод!");
        }
    }

    private static <T extends ConsoleHelper> void operationChoise(T helper) throws IOException, ParseException, ServiceException {
        System.out.println(Menu.SEPARATOR);
        System.out.println(Menu.ACDB_MENU);
        try {
            n = Integer.parseInt(br.readLine());
        } catch (NumberFormatException ignored) {
        }
        switch (n) {
            case 0:
                break;
            case 1:
                helper.addNew();
                helper.printList();
                break;
            case 2:
                helper.update();
                helper.printList();
                break;
            case 3:
                helper.delete();
                helper.printList();
                break;
            case 4:
                currentScreen = Screens.HOME;
                break;
            case 9:
                currentScreen = Screens.HOME;
                currentUser = null;
                break;
            default:
                System.err.println("Неверный ввод!");
        }
    }
}
