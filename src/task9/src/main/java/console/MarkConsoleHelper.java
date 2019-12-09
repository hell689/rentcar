package console;

import domain.Mark;
import service.MarkService;
import service.exceptions.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;

public class MarkConsoleHelper implements ConsoleHelper {

    private MarkService markService;
    private BufferedReader br;

    public MarkConsoleHelper(MarkService markService, BufferedReader br) {
        this.markService = markService;
        this.br = br;
    }

    @Override
    public void addNew() throws IOException {
        Mark mark = new Mark();
        System.out.println("Введите марку автомобиля: ");
        mark.setMark(br.readLine());
        try {
            markService.saveMark(mark);
        } catch (ServiceException e) {
            System.out.println("Марка " + mark.getMark() + " уже имеется в базе данных");
        }
    }

    @Override
    public void delete() throws IOException, ServiceException {
        System.out.println("Введите номер удаляемой марки: ");
        markService.deleteMark(Long.parseLong(br.readLine()));
    }

    @Override
    public void update() throws IOException {
        Mark mark = new Mark();
        System.out.println("Введите номер изменяемой марки автомобиля: ");
        mark.setId(Long.parseLong(br.readLine()));
        System.out.println("Введите марку автомобиля: ");
        mark.setMark(br.readLine());
        try {
            markService.saveMark(mark);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printList() {
        System.out.println("Marks:");
        markService.getMarks().forEach(mark -> System.out.println(mark.getId() + ": " + mark.getMark()));
    }
}
