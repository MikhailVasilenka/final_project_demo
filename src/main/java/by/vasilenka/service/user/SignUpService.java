package by.vasilenka.service.user;

import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.domain.User;
import by.vasilenka.service.exception.ServiceException;

public class SignUpService extends UserService {

    public User signUp(String login, String password,String name, String lastName, String email,String address) throws ServiceException {
        User user = new User(login, password,name,lastName, email,address);

        try {
            return userRepository.add(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to sign-up user", e);
        }
    }
}
