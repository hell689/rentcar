package net.rentcar.servlet.command.request;

import net.rentcar.servlet.command.FrontCommand;
import domain.*;
import service.RequestService;
import service.exceptions.ServiceException;
import util.TextProcessor;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestSaveCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String errorMessage = null;
        RequestService requestService = (RequestService) ctx.getBean("requestService");
        try {
            Long markId = Long.valueOf(request.getParameter("mark"));
            Long colorId = Long.valueOf(request.getParameter("color"));
            Long gearboxId = Long.valueOf(request.getParameter("gearbox"));
            float volume = Float.valueOf(request.getParameter("volume"));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = df.parse(request.getParameter("startDate"));
            Date endDate = df.parse(request.getParameter("endDate"));
            String comment = request.getParameter("comment");
            User user = (User) request.getSession(false).getAttribute("currentUser");

            if (markId > 0 && colorId > 0 && gearboxId > 0 && volume > 0 && startDate.after(new Date()) && startDate.before(endDate)) {
                Mark mark = new Mark();
                mark.setId(markId);
                Color color = new Color();
                color.setId(colorId);
                Gearbox gearbox = new Gearbox();
                gearbox.setId(gearboxId);
                Request newRequest = new Request();
                newRequest.setMark(mark);
                newRequest.setColor(color);
                newRequest.setGearbox(gearbox);
                newRequest.setVolume(volume);
                newRequest.setStartDate(startDate);
                newRequest.setEndDate(endDate);
                newRequest.setComment((new TextProcessor()).formattedText(comment));
                newRequest.setUser(user);
                newRequest.setProcessed(false);
                try {
                    requestService.saveRequest(newRequest);
                    redirect("../request.html");
                } catch (ServiceException e) {
                    errorMessage = URLEncoder.encode("Извините, не удалось сохранить заявку! Проверте правильность заполнения!", "UTF8");
                    System.err.println(e);
                    redirect("../request.html?errorMessage=" + errorMessage);
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            errorMessage = URLEncoder.encode("Ошибка сохранения заявки! Проверте правильность заполнения!", "UTF8");
            redirect("../request.html?errorMessage=" + errorMessage);
        }
    }
}
