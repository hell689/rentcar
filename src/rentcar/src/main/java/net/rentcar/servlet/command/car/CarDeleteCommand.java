package net.rentcar.servlet.command.car;

import net.rentcar.servlet.command.FrontCommand;
import service.CarService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class CarDeleteCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        Long id = null;
        String errorMessage = null;
        try {
            id = Long.parseLong(request.getParameter("deleteId"));
        } catch (NumberFormatException ignored) {
        }
        CarService carService = (CarService) ctx.getBean("carService");
        if (id != null) {
            try {
                carService.deleteCar(id);
                redirect("../car.html");
            } catch (ServiceException e) {
                errorMessage = URLEncoder.encode("Ошибка при удалении автомобиля. Возможно он используется в системе.", "UTF8");
                redirect("../car.html?errorMessage=" + errorMessage);
            }
        }
    }
}
