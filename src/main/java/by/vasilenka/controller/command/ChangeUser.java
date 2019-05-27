package by.vasilenka.controller.command;

import by.vasilenka.domain.User;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.user.CRUDUserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

public class ChangeUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
       String login = request.getParameter("login");
        String role = request.getParameter("role");
        CRUDUserService userService = new CRUDUserService();
        try {
            User user = userService.getByLogin(login);
            if (user == null) {
                throw new CommandException("can't get user by Id");
            }
            user.setRole(role);
            userService.update(user);
            Gson gson = new Gson();
            return gson.toJson(user);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
