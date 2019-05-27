package by.vasilenka.controller.command;

import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class UpdateCart implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.cart-element");
    }
}
