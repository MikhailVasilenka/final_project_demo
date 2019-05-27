package by.vasilenka.controller.command;

import by.vasilenka.controller.command.redirect.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Command Provider
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<String, Command> commandMap = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {
        commandMap.put("deleteUser", new DeleteUser());
        commandMap.put("login", new Login());
        commandMap.put("changePassword", new ChangePassword());
        commandMap.put("signUp", new SignUp());
        commandMap.put("toSignUp", new ToSignUp());
        commandMap.put("toAdmin", new ToAdmin());
        commandMap.put("deleteDrug", new DeleteDrug());
        commandMap.put("toStartPage", new ToHome());
        commandMap.put("toAccount", new ToAccount());
        commandMap.put("toLogin", new ToLogin());
        commandMap.put("toShop", new ToShop());
        commandMap.put("logout", new Logout());
        commandMap.put("getDrugList", new GetDrugList());
        commandMap.put("doInitialRedirectCommand", new DoInitialCommand());
        commandMap.put("toForgotPassword", new ToForgotPassword());
        commandMap.put("toCart", new ToCart());
        commandMap.put("getDrugsByName", new GetDrugsByName());
        commandMap.put("isUserLogin", new IsUserLogin());
        commandMap.put("toOrder", new ToOrder());
        commandMap.put("makeOrder", new MakeOrder());
        commandMap.put("pay", new Pay());
        commandMap.put("getPrescription", new RequestPrescription());
        commandMap.put("getDrugsInfo", new GetDrugsInfo());
        commandMap.put("addDrug", new AddDrug());
        commandMap.put("confirmReceipt", new ConfirmReceipt());
        commandMap.put("givePrescription", new GivePrescription());
        commandMap.put("getUserById", new GetUserById());
        commandMap.put("getItemsByName", new GetItemsByName());
        commandMap.put("updateCart", new UpdateCart());
        commandMap.put("getTableLocale", new GetTableLocale());
        commandMap.put("changeUser", new ChangeUser());
        commandMap.put("changeDrug", new ChangeDrug());
        commandMap.put("changeUserPassword", new ChangeUserPassword());

    }

    /**
     * Return command by name
     * @param command name
     * @return command implementation
     */
    public Command takeCommand(String command) {
       return commandMap.get(command);
    }
}
