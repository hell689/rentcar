package net.rentcar.servlet.command.request;

import net.rentcar.servlet.command.FrontCommand;
import service.RequestService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class RequestDeleteCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        Long id = null;
        String errorMessage = null;
        try {
            id = Long.parseLong(request.getParameter("deleteId"));
        } catch (NumberFormatException ignored) {
        }
        RequestService requestService = (RequestService) ctx.getBean("requestService");
        if (id != null) {
            try {
                requestService.deleteRequest(id);
                redirect("../request.html");
            } catch (ServiceException e) {
                errorMessage = URLEncoder.encode("Ошибка при удалении запроса. Возможно он используется в системе.", "UTF8");
                redirect("../request.html?errorMessage=" + errorMessage);
            }
        }
    }
}
