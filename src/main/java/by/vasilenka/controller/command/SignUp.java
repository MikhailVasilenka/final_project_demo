package by.vasilenka.controller.command;

import by.vasilenka.domain.User;
import by.vasilenka.service.user.PasswordService;
import by.vasilenka.service.user.UserPresentChecker;
import by.vasilenka.service.user.SignUpService;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.validation.*;
import by.vasilenka.util.PathManager;
import by.vasilenka.util.RoleEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUp implements Command {
    private static Logger LOGGER = LogManager.getLogger(SignUp.class);

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String address = req.getParameter("address");

        ValidationService loginValidator = new LoginValidator();
        ValidationService passwordValidator = new PasswordValidator();
        ValidationService emailValidator = new EmailValidator();
        ValidationService lengthValidator = new LengthValidator();
        if (login == null || !loginValidator.isValid(login)) {
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up") + "&error=invalidLogin";
        }
        if (password == null || !passwordValidator.isValid(password)) {
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up") + "&error=invalidPassword";
        }
        if (email == null || !emailValidator.isValid(email)) {
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up") + "&error=invalidEmail";
        }
        if (address == null || !lengthValidator.isValid(address)) {
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up") + "&error=invalidAddress";
        }
        if (name == null || !lengthValidator.isValid(name)) {
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up") + "&error=invalidName";
        }
        if (lastName == null || !lengthValidator.isValid(lastName)) {
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up") + "&error=invalidLastName";
        }
        UserPresentChecker presentChecker = new UserPresentChecker();
        SignUpService signUpService = new SignUpService();
        PasswordService passwordService = new PasswordService();
        try {
            password = passwordService.passwordHash(password);
            if (presentChecker.isReservedLogin(login)) {
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.sign.up") + "&error=reservedLogin";
            }
            if (presentChecker.isReservedEmail(email)) {
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.sign.up") + "&error=reservedEmail";
            }
            User user = signUpService.signUp(login, password, name, lastName, email, address);
            LOGGER.info("user: " + user.getLogin() + " just create an account");
            session.setAttribute("user", user);
            session.setAttribute("role", RoleEnum.valueOf(user.getRole()));
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.account");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
