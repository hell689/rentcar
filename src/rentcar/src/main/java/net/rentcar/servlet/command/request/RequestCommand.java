package net.rentcar.servlet.command.request;

import net.rentcar.servlet.command.FrontCommand;
import domain.*;
import service.ColorService;
import service.GearboxService;
import service.MarkService;
import service.RequestService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class RequestCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("currentUser");
        List<Request> requests;
        if (user.getRole().equals(Role.CLIENT)) {
            requests = ((RequestService) ctx.getBean("requestService")).getUserRequests(user);
        } else {
            requests = ((RequestService) ctx.getBean("requestService")).getRequests();
        }
        List<Mark> marks = ((MarkService) ctx.getBean("markService")).getMarks();
        List<Color> colors = ((ColorService) ctx.getBean("colorService")).getColors();
        List<Gearbox> gearboxes = ((GearboxService) ctx.getBean("gearboxService")).getGearboxes();
        request.setAttribute("requests", requests);
        request.setAttribute("marks", marks);
        request.setAttribute("colors", colors);
        request.setAttribute("gearboxes", gearboxes);
        forward("request");
    }
}
