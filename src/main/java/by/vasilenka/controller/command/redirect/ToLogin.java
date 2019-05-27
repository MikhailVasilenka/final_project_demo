package by.vasilenka.controller.command.redirect;

import by.vasilenka.controller.command.Command;
import by.vasilenka.controller.command.CommandException;
import by.vasilenka.util.RoleEnum;
import by.vasilenka.controller.command.Router;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToLogin implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        if (!role.equals(RoleEnum.GUEST)){
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.initial");
        }
        String errorType = request.getParameter("error");
        request.setAttribute("error",errorType);
        String message = request.getParameter("message");
        request.setAttribute("message",message);
        request.setAttribute("route",Router.Type.FORWARD);
        return PathManager.getProperty("forward.login");
    }
}
