package by.vasilenka.repository.specification;

import by.vasilenka.repository.AbstractJdbcRepository;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.impl.JdbcRepositoryFactory;
import by.vasilenka.repository.impl.UserRepository;
import by.vasilenka.repository.specification.user.GetByEmail;
import by.vasilenka.repository.specification.user.GetUserById;
import by.vasilenka.repository.specification.user.GetByLogin;
import by.vasilenka.domain.User;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    private static Connection connection;
    private static Statement statement;
    private static Repository<User, Integer> userDao;

    @BeforeClass
    public static void connect() throws SQLException, ClassNotFoundException, IOException, RepositoryException {
        InputStream inputStream = UserRepositoryTest.class.getResourceAsStream("/db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        JdbcRepositoryFactory factory = JdbcRepositoryFactory.getInstance();
        Supplier<UserRepository> userCreator = (UserRepository::new);
        userDao = userCreator.get();
        AbstractJdbcRepository<User, Integer> abstrDao = (AbstractJdbcRepository<User, Integer>) userDao;
        abstrDao.setConnection(connection);
        statement = connection.createStatement();
        statement.executeQuery("CREATE TABLE IF NOT EXISTS user (\n" +
                "  id int IDENTITY PRIMARY KEY, \n" +
                "  login varchar(45),\n" +
                "  password varchar (45),\n" +
                "  firstname varchar(45) ,\n" +
                "  lastname varchar(45) ,\n" +
                "  email varchar(45) ,\n" +
                "  role varchar(45), " +
                "address varchar(45))");

    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void clean() throws SQLException, ClassNotFoundException {
        statement = connection.createStatement();
        statement.executeQuery("DELETE FROM user");
        statement.executeQuery("ALTER TABLE user ALTER COLUMN id RESTART WITH 0");
    }

    private List<User> getBySpecification(User user, Specification<User> specification) throws SQLException, RepositoryException {
        statement.executeQuery(" INSERT INTO user (login,password,firstname,lastname,email,role,address) " +
                "VALUES('mike11','TestPassword','Mike','Vas','@mail','client','address')");
        statement.executeQuery(" INSERT INTO user (login,role,email) " +
                "VALUES('mike22','client','@mail')");
        return userDao.getQuery(user, specification);
    }

    @Test
    public void GetByEmail() throws SQLException, RepositoryException {
        User user = new User(0, "mike11", "TestPassword", "Mike", "Vas", "@mail", "client");
        Specification<User> spec = new GetByEmail();
        User expectedUser1 = new User(0, "mike11", "TestPassword", "Mike", "Vas", "@mail", "client","address");
        User expectedUser2 = new User(1, "mike22", null, null, null, "@mail", "client",null);
        List<User> expectedList = new ArrayList<>(Arrays.asList(expectedUser1,expectedUser2));
        List<User> actualList = getBySpecification(user, spec);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getById() throws RepositoryException, SQLException {
        User user = new User(0, "mike11", "TestPassword", "Mike", "Vas", "@mail", "client","address");
        Specification<User> spec = new GetUserById();
        User expectedUser1 = new User(0, "mike11", "TestPassword", "Mike", "Vas", "@mail", "client","address");
        List<User> expectedList = new ArrayList<>(Arrays.asList(expectedUser1));
        List<User> actualList = getBySpecification(user, spec);
        assertEquals(expectedList, actualList);
    }
    @Test
    public void GetByLogin() throws RepositoryException, SQLException {
        User user = new User(0, "mike11", "TestPassword", "Mike", "Vas", "@mail", "client","address");
        Specification<User> spec = new GetByLogin();
        User expectedUser1 = new User(0, "mike11", "TestPassword", "Mike", "Vas", "@mail", "client","address");
        List<User> expectedList = new ArrayList<>(Arrays.asList(expectedUser1));
        List<User> actualList = getBySpecification(user, spec);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void add() throws RepositoryException, SQLException {
        User expectedUser = new User(0, "mike11", "TestPassword", "Mike", "Vas", "@mail", "client",null);
        User actualUser = userDao.add(expectedUser);
        assertEquals(expectedUser, actualUser);
    }

}