package net.rentcar.servlet.command.color;

import net.rentcar.servlet.command.FrontCommand;
import domain.Color;
import service.ColorService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class ColorSaveCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String colorName = null;
        String errorMessage = null;
        colorName = request.getParameter("color");
        ColorService colorService = (ColorService) ctx.getBean("colorService");
        if (!colorName.isEmpty()) {
            Color color = new Color();
            color.setColor(colorName);
            try {
                colorService.saveColor(color);
                redirect("../color.html");
            } catch (ServiceException e) {
                errorMessage = URLEncoder.encode("Ошибка сохранения цвета!", "UTF8");
                redirect("../color.html?errorMessage=" + errorMessage);
            }
        } else {
            errorMessage = URLEncoder.encode("Значение не может быть пустым!", "UTF8");
            redirect("../color.html?errorMessage=" + errorMessage);
        }
    }
}
