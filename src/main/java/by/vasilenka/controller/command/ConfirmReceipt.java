package by.vasilenka.controller.command;

import by.vasilenka.domain.Order;
import by.vasilenka.domain.User;
import by.vasilenka.service.OrderService;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConfirmReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String parameter = request.getParameter("id");
        User user = (User)session.getAttribute("user");
        int orderId = Integer.parseInt(parameter);
        OrderService service = new OrderService();
        try {
            Order order = service.getOrderById(orderId);
            service.confirm(order);
            List<Order> orderList = service.getAllUserOrders(user.getId());
            request.setAttribute("orderList",orderList);
            request.setAttribute("route", Router.Type.FORWARD);
            return PathManager.getProperty("forward.order-element");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
