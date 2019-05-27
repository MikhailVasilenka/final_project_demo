package by.vasilenka.controller.command.redirect;

import by.vasilenka.controller.command.Command;
import by.vasilenka.controller.command.CommandException;
import by.vasilenka.util.RoleEnum;
import by.vasilenka.controller.command.Router;
import by.vasilenka.domain.Drug;
import by.vasilenka.domain.User;
import by.vasilenka.service.drug.CrudDrugService;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.user.CRUDUserService;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAdmin implements Command,RedirectCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        if (!role.equals(RoleEnum.ADMIN)){
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.home");
        }
        CrudDrugService drugService = new CrudDrugService();
        CRUDUserService userService = new CRUDUserService();
        try {
            List<Drug> drugList = drugService.getAll();
            List<User> userList = userService.getAll();
            User admin = null;
            for (User user:userList) {
                if (user.getRole().equalsIgnoreCase("admin")){
                    admin = user;
                }
            }
            if (admin!=null){
                userList.remove(admin);
            }
            req.setAttribute("drugList",drugList);
            req.setAttribute("userList",userList);
            req.setAttribute("route",Router.Type.FORWARD);
            return PathManager.getProperty("forward.admin");
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
