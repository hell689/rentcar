package console;

import domain.Gearbox;
import service.GearboxService;
import service.exceptions.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;

public class GearboxConsoleHelper implements ConsoleHelper {

    private GearboxService gearboxService;
    private BufferedReader br;

    public GearboxConsoleHelper(GearboxService gearboxService, BufferedReader br) {
        this.gearboxService = gearboxService;
        this.br = br;
    }

    @Override
    public void addNew() throws IOException {
        Gearbox gearbox = new Gearbox();
        System.out.println("Введите вид коробки передач: ");
        gearbox.setGearbox(br.readLine());
        try {
            gearboxService.saveGearbox(gearbox);
        } catch (ServiceException e) {
            System.out.println("Коробка передач " + gearbox.getGearbox() + " уже имеется в базе данных!");
        }

    }

    @Override
    public void delete() throws IOException, ServiceException {
        System.out.println("Введите номер удаляемой коробки: ");
        gearboxService.deleteGearbox(Long.parseLong(br.readLine()));
    }

    @Override
    public void update() throws IOException {
        Gearbox gearbox = new Gearbox();
        System.out.println("Введите номер изменяемой коробки передач: ");
        gearbox.setId(Long.parseLong(br.readLine()));
        System.out.println("Введите новое значение: ");
        gearbox.setGearbox(br.readLine());
        try {
            gearboxService.saveGearbox(gearbox);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printList() {
        System.out.println("Gearboxes:");
        gearboxService.getGearboxes().forEach(gearbox -> System.out.println(gearbox.getId() + ": " + gearbox.getGearbox()));
    }
}
