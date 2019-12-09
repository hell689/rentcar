package net.rentcar.servlet.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class IndexCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward("index");
    }
}
