package by.vasilenka.service.user;

import by.vasilenka.repository.RepositoryFactory;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.impl.JdbcRepositoryFactory;
import by.vasilenka.repository.impl.UserRepository;
import by.vasilenka.domain.User;

public abstract class UserService {
    protected RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    protected Repository<User, Integer> userRepository = repositoryFactory.getRepository(UserRepository::new);

    public UserService() {
    }

}