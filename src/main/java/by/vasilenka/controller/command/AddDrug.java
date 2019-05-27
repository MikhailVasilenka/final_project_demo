package by.vasilenka.controller.command;

import by.vasilenka.domain.Drug;
import by.vasilenka.domain.Manufacturer;
import by.vasilenka.domain.ReleaseForm;
import by.vasilenka.service.ManufacturerService;
import by.vasilenka.service.ReleaseFromService;
import by.vasilenka.service.drug.CrudDrugService;
import by.vasilenka.service.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

public class AddDrug implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String description = request.getParameter("releaseForm");
        String manufacturerName = request.getParameter("manufacturer");
        String name = request.getParameter("name");
        String isPrescriptionRequired = request.getParameter("prescription.given");
        String price = request.getParameter("price");
        String availableAmount = request.getParameter("availableAmount");
        CrudDrugService drugService = new CrudDrugService();
        ManufacturerService manufacturerService = new ManufacturerService();
        ReleaseFromService releaseFromService = new ReleaseFromService();
        if (name.equals("")){
            return "emptyName";
        }
        try {
            if (Integer.parseInt(price)<=0) {
                return "invalidPrice";
            }
        }catch (NumberFormatException e){
            return "invalidPrice";
        }
        try {
            if (Integer.parseInt(availableAmount)<=0) {
                return "invalidPrice";
            }
        }catch (NumberFormatException e){
            return "invalidPrice";
        }
        try {
            ReleaseForm releaseForm = releaseFromService.getByDescription(description);
            Manufacturer manufacturer = manufacturerService.getByName(manufacturerName);
            Drug drug = new Drug(name, Boolean.valueOf(isPrescriptionRequired), Integer.parseInt(price), manufacturer, releaseForm,Integer.parseInt(availableAmount));
            drug = drugService.add(drug);
            Gson gson = new Gson();
            return gson.toJson(drug);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
