package by.vasilenka.controller.command.redirect;

import by.vasilenka.controller.command.Command;
import by.vasilenka.controller.command.CommandException;
import by.vasilenka.domain.Order;
import by.vasilenka.domain.Prescription;
import by.vasilenka.domain.User;
import by.vasilenka.service.OrderService;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.prescription.PrescriptionService;
import by.vasilenka.util.RoleEnum;
import by.vasilenka.controller.command.Router;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAccount implements Command,RedirectCommand{
    private HttpSession session;
    private void userDataPrepare(HttpServletRequest request) throws CommandException {
        OrderService service = new OrderService();
        User user = (User)session.getAttribute("user");
        try {
            List<Order> orderList = service.getAllUserOrders(user.getId());
            request.setAttribute("orderList",orderList);
            String message = request.getParameter("message");
            request.setAttribute("message",message);
            String errorType = request.getParameter("error");
            request.setAttribute("error",errorType);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
    private void doctorDataPrepare(HttpServletRequest request) throws CommandException {
        PrescriptionService service = new PrescriptionService();
        User user = (User)session.getAttribute("user");
        List<Prescription> prescriptionList = null;
        try {
            prescriptionList = service.getAllDoctorPrescriptions(user.getId());
            request.setAttribute("prescriptionList",prescriptionList);
            String errorType = request.getParameter("error");
            String message = request.getParameter("message");
            request.setAttribute("message",message);
            request.setAttribute("error",errorType);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        session = request.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        switch (role) {
            case ADMIN:
                request.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.admin");
            case DOCTOR:
               doctorDataPrepare(request);
                request.setAttribute("route", Router.Type.FORWARD);
                return PathManager.getProperty("forward.doctor");
            case CLIENT:
                userDataPrepare(request);
                request.setAttribute("route", Router.Type.FORWARD);
                return PathManager.getProperty("forward.account");
            case GUEST:
                request.setAttribute("route", Router.Type.FORWARD);
                return PathManager.getProperty("forward.login");
            default:
                throw new CommandException();
        }
    }
}
