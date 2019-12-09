package console;

import service.exceptions.ServiceException;

import java.io.IOException;
import java.text.ParseException;

public interface ConsoleHelper {

    void addNew() throws IOException, ParseException, ServiceException;

    void delete() throws IOException, ServiceException;

    void update() throws IOException, ParseException, ServiceException;

    void printList();
}
