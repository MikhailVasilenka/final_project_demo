package by.vasilenka.controller.command;

import by.vasilenka.domain.User;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.user.CRUDUserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

public class GetUserById implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String idStr = request.getParameter("id");
        if (idStr==null){
            throw new IllegalArgumentException();
        }
        int id = Integer.parseInt(idStr);
        CRUDUserService userService = new CRUDUserService();
        try {
            User user = userService.getById(id);
            Gson gson = new Gson();
            return gson.toJson(user);
        } catch (ServiceException |IllegalArgumentException e) {
            throw new CommandException();
        }
    }
}
