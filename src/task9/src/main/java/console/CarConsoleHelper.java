package console;

import domain.*;
import service.CarService;
import service.exceptions.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

public class CarConsoleHelper implements ConsoleHelper {

    private CarService carService;
    private ColorConsoleHelper colorConsoleHelper;
    private MarkConsoleHelper markConsoleHelper;
    private GearboxConsoleHelper gearboxConsoleHelper;
    private BufferedReader br;

    public CarConsoleHelper(CarService carService, BufferedReader br, ColorConsoleHelper colorConsoleHelper,
                            MarkConsoleHelper markConsoleHelper, GearboxConsoleHelper gearboxConsoleHelper) {
        this.carService = carService;
        this.colorConsoleHelper = colorConsoleHelper;
        this.markConsoleHelper = markConsoleHelper;
        this.gearboxConsoleHelper = gearboxConsoleHelper;
        this.br = br;
    }

    @Override
    public void addNew() throws IOException, ServiceException {
        Car car = new Car();
        createCar(car);
        carService.saveCar(car);
    }

    @Override
    public void delete() throws IOException, ServiceException {
        System.out.println("Введите номер удаляемого автомобиля: ");
        carService.deleteCar(Long.parseLong(br.readLine()));
    }

    @Override
    public void update() throws IOException, ServiceException {
        Car car = new Car();
        System.out.println("Введите номер изменяемого автомобиля: ");
        car.setId(Long.parseLong(br.readLine()));
        createCar(car);
        carService.saveCar(car);
    }

    @Override
    public void printList() {
        System.out.println("Cars:");
        carService.getCars().forEach(car -> System.out.println(car.getId() + ": " + car.getMark().getMark() +
                ", коробка: " + car.getGearbox().getGearbox() + ", объём: " + car.getVolume() +
                ", цвет: " + car.getColor().getColor()));
    }

    public void printFreeCars() {
        System.out.println("Свободные автомобили:");
        carService.getFreeCars(new Date()).forEach(car -> System.out.println(car.getId() + ": " + car.getMark().getMark() +
                ", коробка: " + car.getGearbox().getGearbox() + ", объём: " + car.getVolume() +
                ", цвет: " + car.getColor().getColor()));
    }

    public void printSuitableCars (Request request) {
        System.out.println("Наиболее подходящие автомобили:");
        carService.getSuitableCars(request).get("Most suitable cars").forEach(car -> System.out.println(car.getId() + ": " + car.getMark().getMark() +
                ", коробка: " + car.getGearbox().getGearbox() + ", объём: " + car.getVolume() +
                ", цвет: " + car.getColor().getColor()));
    }

    private void createCar(Car car) throws IOException {
        markConsoleHelper.printList();
        System.out.println("Введите номер марки авто: ");
        car.setMark(new Mark());
        car.getMark().setId(Long.parseLong(br.readLine()));
        colorConsoleHelper.printList();
        System.out.println("Введите номер цвета авто: ");
        car.setColor(new Color());
        car.getColor().setId(Long.parseLong(br.readLine()));
        gearboxConsoleHelper.printList();
        System.out.println("Введите номер коробки передач: ");
        car.setGearbox(new Gearbox());
        car.getGearbox().setId(Long.parseLong(br.readLine()));
        System.out.println("Введите объем двигателя авто: ");
        car.setVolume(Float.parseFloat(br.readLine()));
    }

}
