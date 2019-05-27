package by.vasilenka.repository.specification.user;

import by.vasilenka.domain.User;

import java.sql.*;
import java.util.List;

public class GetByLogin implements GetUserSpecification {
    @Override
    public List<User> get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user WHERE login = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getLogin());
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
