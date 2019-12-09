package net.rentcar.servlet.command.gearbox;

import net.rentcar.servlet.command.FrontCommand;
import domain.Gearbox;
import service.GearboxService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GearboxCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        List<Gearbox> gearboxes = ((GearboxService) ctx.getBean("gearboxService")).getGearboxes();
        request.setAttribute("gearboxes", gearboxes);
        forward("gearbox");
    }
}
