package by.vasilenka.service.user;

import by.vasilenka.domain.User;
import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.specification.Specification;
import by.vasilenka.repository.specification.user.GetByLogin;
import by.vasilenka.service.exception.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class PasswordService extends UserService {

    public String changePassword(String login) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List<User> list = userRepository.getQuery(user, spec);
            if (list.isEmpty()) {
                throw new IllegalArgumentException("can't find user by id");
            }
            user = list.get(0);
            String newPassword = Long.toHexString(Double.doubleToLongBits(Math.random()));
            user.setPassword(passwordHash(newPassword));
            userRepository.update(user);
            return newPassword;
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to change user password. ", e);
        }
    }

    public String passwordHash(String password) throws ServiceException {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passwordByte = sha256.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < passwordByte.length; i++) {
                String s = Integer.toHexString(passwordByte[i] & 0xff);
                if (s.length() == 1) {
                    s = "0" + s;
                }
                sb.append(s);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }

    }

}