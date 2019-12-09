package net.rentcar.servlet.command.car;

import net.rentcar.servlet.command.FrontCommand;
import domain.Car;
import domain.Color;
import domain.Gearbox;
import domain.Mark;
import service.CarService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class CarSaveCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String errorMessage = null;
        CarService carService = (CarService) ctx.getBean("carService");
        try {
            Long markId = Long.valueOf(request.getParameter("mark"));
            Long colorId = Long.valueOf(request.getParameter("color"));
            Long gearboxId = Long.valueOf(request.getParameter("gearbox"));
            float volume = Float.valueOf(request.getParameter("volume"));

            if (markId > 0 && colorId > 0 && gearboxId > 0 && volume > 0) {
                Mark mark = new Mark();
                mark.setId(markId);
                Color color = new Color();
                color.setId(colorId);
                Gearbox gearbox = new Gearbox();
                gearbox.setId(gearboxId);
                Car car = new Car();
                car.setMark(mark);
                car.setColor(color);
                car.setGearbox(gearbox);
                car.setVolume(volume);
                try {
                    carService.saveCar(car);
                    redirect("../car.html");
                } catch (ServiceException e) {
                    errorMessage = URLEncoder.encode("Ошибка сохранения автомобиля!", "UTF8");
                    redirect("../car.html?errorMessage=" + errorMessage);
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            errorMessage = URLEncoder.encode("Ошибка сохранения автомобиля! Необходимо заполнить все значения!", "UTF8");
            redirect("../car.html?errorMessage=" + errorMessage);
        }
    }
}
