package by.vasilenka.service.prescription;

import by.vasilenka.domain.User;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.RepositoryFactory;
import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.impl.JdbcRepositoryFactory;
import by.vasilenka.repository.impl.UserRepository;
import by.vasilenka.repository.specification.Specification;
import by.vasilenka.repository.specification.user.GetByRole;
import by.vasilenka.service.exception.ServiceException;

import java.util.List;

public class RandomDoctorSelectorService implements DoctorSelectorForPrescriptionService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<User, Integer> repository = repositoryFactory.getRepository(UserRepository::new);
    @Override
    public User select() throws ServiceException {
        Specification<User> specification = new GetByRole();
        try {
            User doctor = new User();
            doctor.setRole("doctor");
            List<User> doctorList = repository.getQuery(doctor,specification);
            int randomIndex = (int)(Math.random()* (doctorList.size()-1));
            return doctorList.get(randomIndex);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
