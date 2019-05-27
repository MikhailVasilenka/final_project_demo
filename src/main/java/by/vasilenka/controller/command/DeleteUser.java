package by.vasilenka.controller.command;


import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.user.CRUDUserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUser implements Command {
    public String execute(HttpServletRequest request) throws CommandException {
        CRUDUserService crudUserService = new CRUDUserService();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            crudUserService.delete(id);
            return "User has been deleted";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
