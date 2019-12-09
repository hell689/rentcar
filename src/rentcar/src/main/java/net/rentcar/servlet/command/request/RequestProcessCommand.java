package net.rentcar.servlet.command.request;

import net.rentcar.servlet.command.FrontCommand;
import domain.Car;
import domain.Request;
import service.CarService;
import service.RequestService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class RequestProcessCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        Long requestId = Long.valueOf(request.getParameter("requestId"));
        if (requestId > 0) {
            RequestService requestService = ((RequestService) ctx.getBean("requestService"));
            Request processedRequest = requestService.getRequest(requestId);
            if (processedRequest.isProcessed()) {
                redirect("../request.html");
            } else {
                CarService carService = ((CarService) ctx.getBean("carService"));
                List<Car> freeCars = carService.getFreeCars(processedRequest.getStartDate());
                List<Car> suitableCars = carService.getSuitableCars(processedRequest).get("Most suitable cars");
                if (!suitableCars.isEmpty()) request.setAttribute("suitableCars", suitableCars);
                request.setAttribute("freeCars", freeCars);
                request.setAttribute("processedRequest", processedRequest);
                forward("addrent");
            }
        }
    }
}
