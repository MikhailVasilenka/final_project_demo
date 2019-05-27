package by.vasilenka.controller.command;

import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.prescription.PrescriptionService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GivePrescription implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        String dateString = request.getParameter("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(description==null){
            description = "";
        }
        try {
            Date validityDateParsed = format.parse(dateString);
            java.sql.Date validityDate = new java.sql.Date(validityDateParsed.getTime());
            java.sql.Date issueDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
            PrescriptionService prescriptionService = new PrescriptionService();
            prescriptionService.givePrescription(id, description, issueDate, validityDate);
            return "true";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        catch (ParseException e) {
            return "false";
        }
    }
}
