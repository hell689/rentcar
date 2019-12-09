package net.rentcar.servlet.command.rent;

import net.rentcar.servlet.command.FrontCommand;
import domain.*;
import service.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class RentCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("currentUser");
        List<Rent> rents;
        if (user.getRole().equals(Role.CLIENT)) {
            rents = ((RentService) ctx.getBean("rentService")).getUserRents(user);
        } else {
            rents = ((RentService) ctx.getBean("rentService")).getRents();
        }
        request.setAttribute("rents", rents);
        forward("rent");
    }
}
