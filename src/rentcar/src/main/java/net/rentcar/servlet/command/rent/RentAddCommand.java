package net.rentcar.servlet.command.rent;

import net.rentcar.servlet.command.FrontCommand;
import domain.Car;
import domain.Rent;
import domain.Request;
import service.CarService;
import service.RentService;
import service.RequestService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;

public class RentAddCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        Long requestId = Long.valueOf(request.getParameter("requestId"));
        Long carId = Long.valueOf(request.getParameter("carId"));
        if (requestId > 0) {
            RequestService requestService = ((RequestService) ctx.getBean("requestService"));
            Request processedRequest = requestService.getRequest(requestId);
            if (processedRequest.isProcessed()) {
                redirect("../request.html");
            } else {
                RentService rentService = ((RentService) ctx.getBean("rentService"));
                CarService carService = ((CarService) ctx.getBean("carService"));
                Car car = carService.getCar(carId);
                Rent rent = new Rent();
                rent.setRequest(processedRequest);
                rent.setCar(car);
                rent.setStartDate(processedRequest.getStartDate());
                rent.setEndDate(processedRequest.getEndDate());
                try {
                    rentService.saveRent(rent);
                    processedRequest.setProcessed(true);
                    requestService.saveRequest(processedRequest);
                    redirect("../rent.html");
                } catch (ServiceException e) {
                    redirect("../request.html");
                }
            }
        } else {
            redirect("../request.html");
        }
    }
}
