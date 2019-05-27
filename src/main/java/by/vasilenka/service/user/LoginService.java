package by.vasilenka.service.user;

import by.vasilenka.domain.User;
import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.specification.Specification;
import by.vasilenka.repository.specification.user.GetByLogin;
import by.vasilenka.service.exception.ServiceException;

import java.util.List;

public class LoginService extends UserService {

    public User login(String login, String password) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List<User> list = userRepository.getQuery(user, spec);
            if (list.isEmpty()) {
                return null;
            }
            user = list.get(0);
            if (!user.getPassword().equals(password)) {
                return null;
            }
            return user;

        } catch (RepositoryException e) {
            throw new ServiceException("Failed to login user", e);
        }
    }
}
