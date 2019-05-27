package by.vasilenka.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IsUserLogin implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
       HttpSession session =  request.getSession();
       Object attribute = session.getAttribute("user");
       if(attribute!=null) {
           return "true";
       }
        else{
            return "false";
       }
    }
}
