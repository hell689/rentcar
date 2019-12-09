package net.rentcar.servlet.command.gearbox;

import net.rentcar.servlet.command.FrontCommand;
import service.GearboxService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class GearboxDeleteCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        Long id = null;
        String errorMessage = null;
        try {
            id = Long.parseLong(request.getParameter("deleteId"));
        } catch (NumberFormatException ignored) {
        }
        GearboxService gearboxService = (GearboxService) ctx.getBean("gearboxService");
        if (id != null) {
            try {
                gearboxService.deleteGearbox(id);
                redirect("../gearbox.html");
            } catch (ServiceException e) {
                errorMessage = URLEncoder.encode("Ошибка при удалении коробки передач. Возможно она еще используется в системе.", "UTF8");
                redirect("../gearbox.html?errorMessage=" + errorMessage);
            }
        }
    }
}
