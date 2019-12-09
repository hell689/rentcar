package net.rentcar.servlet.command.color;

import net.rentcar.servlet.command.FrontCommand;
import domain.Color;
import service.ColorService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class ColorCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        List<Color> colors = ((ColorService) ctx.getBean("colorService")).getColors();
        request.setAttribute("colors", colors);
        forward("color");
    }
}
