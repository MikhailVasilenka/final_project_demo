package by.vasilenka.repository.specification.user;

import by.vasilenka.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetUserById implements GetUserSpecification {
    @Override
    public List<User> get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
