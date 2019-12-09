package console;

import domain.Car;
import domain.Rent;
import service.RentService;
import service.exceptions.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;

public class RentConsoleHelper implements ConsoleHelper {

    private RentService rentService;
    private CarConsoleHelper carConsoleHelper;
    private RequestConsoleHelper requestConsoleHelper;
    private BufferedReader br;

    public RentConsoleHelper(RentService rentService, BufferedReader br, CarConsoleHelper carConsoleHelper, RequestConsoleHelper requestConsoleHelper) {
        this.rentService = rentService;
        this.carConsoleHelper = carConsoleHelper;
        this.requestConsoleHelper = requestConsoleHelper;
        this.br = br;
    }

    @Override
    public void addNew() throws IOException, ParseException {
        Rent rent = new Rent();
        createRent(rent);
        try {
            rentService.saveRent(rent);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() throws IOException {
        System.out.println("Введите номер удаляемой аренды: ");
        try {
            rentService.deleteRent(Long.parseLong(br.readLine()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() throws IOException, ParseException {
        Rent rent = new Rent();
        System.out.println("Введите номер изменяемой аренды: ");
        rent.setId(Long.parseLong(br.readLine()));
        createRent(rent);
        try {
            rentService.saveRent(rent);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printList() {
        System.out.println("Rents:");
        rentService.getRents().forEach(rent -> System.out.println(rent.getId() + ": " + " Запрос №" + rent.getRequest().getId() +
                " пользователя " + rent.getRequest().getUser().getLogin() + "; выдаваемый автомобиль: " + rent.getCar() + ", дата начала аренды: " + rent.getStartDate() + ", дата окончания аренды: " +
                rent.getEndDate()));
    }

    private void createRent(Rent rent) throws IOException, ParseException {
        requestConsoleHelper.printList();
        System.out.println("Введите номер заявки: ");
        rent.setRequest(requestConsoleHelper.getRequestService().getRequest(Long.parseLong(br.readLine())));
        carConsoleHelper.printFreeCars();
        carConsoleHelper.printSuitableCars(rent.getRequest());
        System.out.println("Введите номер выдаваемого авто: ");
        rent.setCar(new Car());
        rent.getCar().setId(Long.parseLong(br.readLine()));
        rent.setStartDate(rent.getRequest().getStartDate());
        rent.setEndDate(rent.getRequest().getEndDate());
    }
}
