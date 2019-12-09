package net.rentcar.servlet.command.mark;

import net.rentcar.servlet.command.FrontCommand;
import domain.Mark;
import service.MarkService;
import service.exceptions.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

public class MarkSaveCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String markValue = null;
        String errorMessage = null;
        markValue = request.getParameter("mark");
        MarkService markService = (MarkService) ctx.getBean("markService");
        if (!markValue.isEmpty()) {
            Mark mark = new Mark();
            mark.setMark(markValue);
            try {
                markService.saveMark(mark);
                redirect("../mark.html");
            } catch (ServiceException e) {
                errorMessage = URLEncoder.encode("Ошибка сохранения марки!", "UTF8");
                redirect("../mark.html?errorMessage=" + errorMessage);
            }
        } else {
            errorMessage = URLEncoder.encode("Значение не может быть пустым!", "UTF8");
            redirect("../mark.html?errorMessage=" + errorMessage);
        }
    }
}
