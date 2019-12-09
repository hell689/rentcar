package console;

import domain.*;
import service.RequestService;
import service.exceptions.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RequestConsoleHelper implements ConsoleHelper {

    private RequestService requestService;
    private ColorConsoleHelper colorConsoleHelper;
    private MarkConsoleHelper markConsoleHelper;
    private GearboxConsoleHelper gearboxConsoleHelper;
    private User user;
    private BufferedReader br;

    public RequestConsoleHelper(RequestService requestService, BufferedReader br, ColorConsoleHelper colorConsoleHelper,
                                MarkConsoleHelper markConsoleHelper, GearboxConsoleHelper gearboxConsoleHelper, User user) {
        this.requestService = requestService;
        this.colorConsoleHelper = colorConsoleHelper;
        this.markConsoleHelper = markConsoleHelper;
        this.gearboxConsoleHelper = gearboxConsoleHelper;
        this.user = user;
        this.br = br;
    }

    @Override
    public void addNew() throws IOException, ParseException {
        Request request = new Request();
        createRequest(request);
        try {
            requestService.saveRequest(request);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() throws IOException, ServiceException {
        System.out.println("Введите номер удаляемого запроса: ");
        requestService.deleteRequest(Long.parseLong(br.readLine()));
    }

    @Override
    public void update() throws IOException, ParseException {
        Request request = new Request();
        System.out.println("Введите номер изменяемого запроса: ");
        request.setId(Long.parseLong(br.readLine()));
        createRequest(request);
        try {
            requestService.saveRequest(request);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printList() {
        if (user.getRole() == Role.ADMINISTRATOR) {
            System.out.println("Requests:");
            requestService.getRequests().forEach(request -> System.out.println(request.getId() + ": " +
                    request.getMark().getMark() + ", коробка: " + request.getGearbox().getGearbox() + ", объём: " +
                    request.getVolume() + ", цвет: " + request.getColor().getColor() + ", дата начала: " +
                    request.getStartDate() + ", дата окончания: " + request.getEndDate() + ", пользователь; " +
                    request.getUser().getLogin() + ", комментарий: " + request.getComment()));
        } else {
            System.out.println(user.getLogin() + " requests");
            requestService.getUserRequests(user).forEach(request -> System.out.println(request.getId() + ": " +
                    request.getMark().getMark() + ", коробка: " + request.getGearbox().getGearbox() + ", объём: " +
                    request.getVolume() + ", цвет: " + request.getColor().getColor() + ", дата начала: " +
                    request.getStartDate() + ", дата окончания: " + request.getEndDate() + ", пользователь; " +
                    request.getUser().getLogin() + ", комментарий: " + request.getComment()));
        }
    }

    private void createRequest(Request request) throws IOException, ParseException {
        markConsoleHelper.printList();
        System.out.println("Введите номер желаемой марки авто: ");
        request.setMark(new Mark());
        request.getMark().setId(Long.parseLong(br.readLine()));
        colorConsoleHelper.printList();
        System.out.println("Введите номер желаемого цвета авто: ");
        request.setColor(new Color());
        request.getColor().setId(Long.parseLong(br.readLine()));
        gearboxConsoleHelper.printList();
        System.out.println("Введите номер необходимой коробки передач: ");
        request.setGearbox(new Gearbox());
        request.getGearbox().setId(Long.parseLong(br.readLine()));
        System.out.println("Введите объем двигателя авто: ");
        request.setVolume(Float.parseFloat(br.readLine().replaceAll(",", ".")));
        System.out.println("Введите дату начала аренды(дд.мм.гггг):");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        request.setStartDate(sdf.parse(br.readLine()));
        System.out.println("Введите дату окончания аренды(дд.мм.гггг):");
        request.setEndDate(sdf.parse(br.readLine()));
        System.out.println("Введите комментарий:");
        request.setComment(br.readLine());
        request.setUser(user);
        request.setProcessed(false);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RequestService getRequestService() {
        return requestService;
    }
}
