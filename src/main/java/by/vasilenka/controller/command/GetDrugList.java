package by.vasilenka.controller.command;

import by.vasilenka.domain.Drug;
import by.vasilenka.service.drug.CrudDrugService;
import by.vasilenka.service.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class GetDrugList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        CrudDrugService drugService = new CrudDrugService();
        try {
            List<Drug> drugList = drugService.getAll();
            List<String> drugNameList = drugList.stream()
                    .map(Drug::getName)
                    .collect(Collectors.toList());
            Gson gson = new Gson();
            String jsonList = gson.toJson(drugNameList);
            return jsonList;
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}

