package by.vasilenka.controller.command.redirect;

import by.vasilenka.controller.command.Command;
import by.vasilenka.controller.command.CommandException;
import by.vasilenka.controller.command.Router;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ToHome implements Command,RedirectCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.home");
    }
}
