package net.rentcar.servlet.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageNotFoundCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        forward("404");
    }
}
