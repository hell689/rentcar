package net.rentcar.servlet.command.mark;

import net.rentcar.servlet.command.FrontCommand;
import domain.Mark;
import service.MarkService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class MarkCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        List<Mark> marks = ((MarkService) ctx.getBean("markService")).getMarks();
        request.setAttribute("marks", marks);
        forward("mark");
    }
}
