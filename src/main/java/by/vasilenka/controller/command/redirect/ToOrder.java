package by.vasilenka.controller.command.redirect;

import by.vasilenka.controller.command.Command;
import by.vasilenka.controller.command.CommandException;
import by.vasilenka.controller.command.Router;
import by.vasilenka.domain.Order;
import by.vasilenka.service.OrderService;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ToOrder implements Command, RedirectCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String parameter = request.getParameter("orderId");
        if(parameter != null) {
            int orderId = Integer.parseInt(parameter);
            OrderService service = new OrderService();
            try {
                Order order = service.getOrderById(orderId);
                request.getSession().setAttribute("order",order);
            } catch (ServiceException e) {
                throw new CommandException();
            }
        }
        request.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.order");
    }
}
