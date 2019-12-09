package net.rentcar.servlet.command.car;

import net.rentcar.servlet.command.FrontCommand;
import domain.Car;
import domain.Color;
import domain.Gearbox;
import domain.Mark;
import service.CarService;
import service.ColorService;
import service.GearboxService;
import service.MarkService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class CarCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        List<Car> cars = ((CarService) ctx.getBean("carService")).getCars();
        List<Mark> marks = ((MarkService) ctx.getBean("markService")).getMarks();
        List<Color> colors = ((ColorService) ctx.getBean("colorService")).getColors();
        List<Gearbox> gearboxes = ((GearboxService) ctx.getBean("gearboxService")).getGearboxes();
        request.setAttribute("cars", cars);
        request.setAttribute("marks", marks);
        request.setAttribute("colors", colors);
        request.setAttribute("gearboxes", gearboxes);
        forward("car");
    }
}
