package by.vasilenka.controller.command;

import by.vasilenka.service.EmailService;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.user.PasswordService;
import by.vasilenka.service.user.UserPresentChecker;
import by.vasilenka.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ChangePassword implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        PasswordService passwordService = new PasswordService();
        UserPresentChecker presentChecker = new UserPresentChecker();
        try {
            if (!presentChecker.isPresentWithSuchLoginAndEmail(login, email)) {
                request.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.forgot-password") + "&error=incorrectData";
            }
            String newPassword = passwordService.changePassword(login);
            EmailService sender = new EmailService();
            sender.send("Your new password", "There is your new password: " + newPassword, email);
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.login")+"&message=success";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
