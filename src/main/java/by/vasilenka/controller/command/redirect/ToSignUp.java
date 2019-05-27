package by.vasilenka.controller.command.redirect;

import by.vasilenka.controller.command.Command;
import by.vasilenka.controller.command.CommandException;
import by.vasilenka.controller.command.Router;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ToSignUp implements Command,RedirectCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        String errorType = req.getParameter("error");
        req.setAttribute("error",errorType);
        req.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.sign.up");
    }
}
