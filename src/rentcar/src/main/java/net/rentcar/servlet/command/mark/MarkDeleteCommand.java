package net.rentcar.servlet.command.mark;

import net.rentcar.servlet.command.FrontCommand;
import service.MarkService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class MarkDeleteCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        Long id = null;
        String errorMessage = null;
        try {
            id = Long.parseLong(request.getParameter("deleteId"));
        } catch (NumberFormatException ignored) {
        }
        MarkService markService = (MarkService) ctx.getBean("markService");
        if (id != null) {
            try {
                markService.deleteMark(id);
                redirect("../mark.html");
            } catch (ServiceException e) {
                errorMessage = URLEncoder.encode("Ошибка при удалении марки. Возможно она еще используется в системе.", "UTF8");
                redirect("../mark.html?errorMessage=" + errorMessage);
            }
        }
    }
}
