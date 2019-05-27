package by.vasilenka.controller.command.redirect;

import by.vasilenka.controller.command.Command;
import by.vasilenka.controller.command.CommandException;
import by.vasilenka.controller.command.Router;
import by.vasilenka.util.PathManager;
import by.vasilenka.util.RoleEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToShop implements Command,RedirectCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String errorType = request.getParameter("error");
        request.setAttribute("error",errorType);
        request.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.shop");
    }
}