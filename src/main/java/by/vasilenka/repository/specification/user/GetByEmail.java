package by.vasilenka.repository.specification.user;

import by.vasilenka.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetByEmail implements GetUserSpecification {
    @Override
    public List<User> get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
