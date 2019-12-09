package net.rentcar.servlet.command.color;

import net.rentcar.servlet.command.FrontCommand;
import service.ColorService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class ColorDeleteCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        Long id = null;
        String errorMessage = null;
        try {
            id = Long.parseLong(request.getParameter("deleteId"));
        } catch (NumberFormatException ignored) {
        }
        ColorService colorService = (ColorService) ctx.getBean("colorService");
        if (id != null) {
            try {
                colorService.deleteColor(id);
                redirect("../color.html");
            } catch (ServiceException e) {
                errorMessage = URLEncoder.encode("Ошибка при удалении цвета. Возможно он еще используется в системе.", "UTF8");
                redirect("../color.html?errorMessage=" + errorMessage);
            }
        }
    }
}
