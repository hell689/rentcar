package net.rentcar.servlet;

import net.rentcar.servlet.command.FrontCommand;
import net.rentcar.servlet.command.PageNotFoundCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class DispatcherServlet extends HttpServlet {

    private ResourceBundle mappingProps;
    private Map<String, FrontCommand> commands;

    @Override
    public void init() throws ServletException {
        super.init();
        this.mappingProps = ResourceBundle.getBundle("mapping");
        this.commands = new HashMap<>();
        commands.put("404", new PageNotFoundCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        String action = getActionKey(request);
        FrontCommand command = commands.get(action);
        if (Objects.isNull(command)) {
            try {
                Class<?> type = Class.forName(mappingProps.getObject(action).toString());
                command = type.asSubclass(FrontCommand.class).newInstance();
                commands.put(action, command);
            } catch (Exception e) {
                command = commands.get("404");
            }
        }
        return command;
    }

    private String getActionKey(HttpServletRequest request) {
        String path = request.getServletPath();
        int slash = path.indexOf("/");
        int period = path.lastIndexOf(".");
        if (period >= 0 && period > slash) {
            path = path.substring(slash + 1, period);
        }
        return path;
    }
}
