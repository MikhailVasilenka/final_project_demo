package by.vasilenka.service.user;

import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.specification.user.GetByEmail;
import by.vasilenka.repository.specification.user.GetByLogin;
import by.vasilenka.repository.specification.Specification;
import by.vasilenka.domain.User;
import by.vasilenka.repository.specification.user.GetByLoginAndEmail;
import by.vasilenka.service.exception.ServiceException;

import java.util.List;

public class UserPresentChecker extends UserService {
    public  boolean isPresentWithSuchLoginAndEmail(String login, String email) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        try {
            Specification<User> spec = new GetByLoginAndEmail();
            List list = userRepository.getQuery(user, spec);
            return !list.isEmpty();
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to check present of user in base ", e);
        }
    }

    public boolean isReservedLogin(String login) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List list = userRepository.getQuery(user, spec);
            return !list.isEmpty();
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to check present of user in base ", e);
        }
    }

    public boolean isReservedEmail(String email) throws ServiceException {
        User user = new User();
        user.setEmail(email);
        try {
            Specification<User> spec = new GetByEmail();
            List list = userRepository.getQuery(user, spec);
            return !list.isEmpty();
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to check present of user in base ", e);
        }
    }
}
