package by.vasilenka.controller.command;

import by.vasilenka.domain.Order;
import by.vasilenka.service.OrderService;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Pay implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute("order");
        if(attribute==null){
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.order")+"&message=error";
        }
        Order order = (Order)attribute;
        OrderService service = new OrderService();
        try {
            service.pay(order);
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.account")+"&message=success";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
