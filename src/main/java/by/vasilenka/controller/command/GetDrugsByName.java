package by.vasilenka.controller.command;

import by.vasilenka.domain.Drug;
import by.vasilenka.domain.Item;
import by.vasilenka.service.drug.CrudDrugService;
import by.vasilenka.service.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class GetDrugsByName implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String name = request.getParameter("name");
        CrudDrugService drugService = new CrudDrugService();
        try {
            List<Drug> drugList = drugService.getByName(name);
            List<Item> itemList = drugList.stream().map(Item::new).collect(Collectors.toList());
            Gson gson = new Gson();
            return gson.toJson(itemList);
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
