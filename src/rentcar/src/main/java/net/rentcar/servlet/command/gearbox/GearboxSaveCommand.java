package net.rentcar.servlet.command.gearbox;

import net.rentcar.servlet.command.FrontCommand;
import domain.Gearbox;
import service.GearboxService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class GearboxSaveCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String gearboxValue = null;
        String errorMessage = null;
        gearboxValue = request.getParameter("gearbox");
        GearboxService gearboxService = (GearboxService) ctx.getBean("gearboxService");
        if (!gearboxValue.isEmpty()) {
            Gearbox gearbox = new Gearbox();
            gearbox.setGearbox(gearboxValue);
            try {
                gearboxService.saveGearbox(gearbox);
                redirect("../gearbox.html");
            } catch (ServiceException e) {
                errorMessage = URLEncoder.encode("Ошибка сохранения коробки передач!", "UTF8");
                redirect("../gearbox.html?errorMessage=" + errorMessage);
            }
        } else {
            errorMessage = URLEncoder.encode("Значение не может быть пустым!", "UTF8");
            redirect("../gearbox.html?errorMessage=" + errorMessage);
        }
    }
}
