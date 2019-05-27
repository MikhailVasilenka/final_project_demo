package by.vasilenka.controller.command;

import by.vasilenka.domain.User;
import by.vasilenka.service.prescription.DoctorSelectorForPrescriptionService;
import by.vasilenka.service.prescription.PrescriptionService;
import by.vasilenka.service.exception.ServiceException;
import by.vasilenka.service.prescription.RandomDoctorSelectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestPrescription implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        if(user == null){
            return "notLogined";
        }
        int drugId = Integer.parseInt(request.getParameter("drugId"));
        PrescriptionService prescriptionService = new PrescriptionService();
        DoctorSelectorForPrescriptionService selector = new RandomDoctorSelectorService();
        try {
            if (prescriptionService.isUserHasValidPrescription(user.getId(),drugId)){
                return "valid";
            }
            if (!prescriptionService.isUserHasInvalidPrescription(user.getId(),drugId)
                    && prescriptionService.isUserQuirePrescription(user.getId(),drugId)){
                return "alreadyHave";
            }
            User doctor = selector.select();
            prescriptionService.requestPrescription(user.getId(),drugId,doctor.getId());
            return "ok";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
