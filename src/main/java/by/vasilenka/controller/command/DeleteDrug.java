package by.vasilenka.controller.command;

import by.vasilenka.service.drug.CrudDrugService;
import by.vasilenka.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteDrug implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        CrudDrugService crudService = new CrudDrugService();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            crudService.delete(id);
            return "Drug has been deleted";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
