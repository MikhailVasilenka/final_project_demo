package by.vasilenka.service.prescription;

import by.vasilenka.domain.User;
import by.vasilenka.service.exception.ServiceException;

public interface DoctorSelectorForPrescriptionService {
    User  select() throws ServiceException;
}
