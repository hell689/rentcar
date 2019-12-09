package console;

import domain.Color;
import service.ColorService;
import service.exceptions.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;

public class ColorConsoleHelper implements ConsoleHelper {

    private ColorService colorService;
    private BufferedReader br;

    public ColorConsoleHelper(ColorService colorService, BufferedReader br) {
        this.colorService = colorService;
        this.br = br;
    }

    @Override
    public void addNew() throws IOException {
        Color color = new Color();
        System.out.println("Введите цвет: ");
        color.setColor(br.readLine());
        try {
            colorService.saveColor(color);
        } catch (ServiceException e) {
            System.out.println("Цвет " + color.getColor() + " уже имеется в базе данных!");
        }

    }

    @Override
    public void delete() throws IOException, ServiceException {
        System.out.println("Введите номер удаляемого цвета: ");
        colorService.deleteColor(Long.parseLong(br.readLine()));
    }

    @Override
    public void update() throws IOException {
        Color color = new Color();
        System.out.println("Введите номер изменяемого цвета: ");
        color.setId(Long.parseLong(br.readLine()));
        System.out.println("Введите цвет: ");
        color.setColor(br.readLine());
        try {
            colorService.saveColor(color);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printList() {
        System.out.println("Colors:");
        colorService.getColors().forEach(color -> System.out.println(color.getId() + ": " + color.getColor()));
    }
}
