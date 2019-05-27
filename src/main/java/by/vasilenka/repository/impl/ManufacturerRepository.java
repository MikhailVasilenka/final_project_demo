package by.vasilenka.repository.impl;

import by.vasilenka.domain.Manufacturer;
import by.vasilenka.repository.AbstractJdbcRepository;
import by.vasilenka.repository.AutoConnection;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.exception.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManufacturerRepository extends AbstractJdbcRepository<Manufacturer,Integer> implements Repository<Manufacturer,Integer> {
    @AutoConnection
    @Override
    public Manufacturer add(Manufacturer manufacturer) throws RepositoryException {
        String query = "INSERT INTO manufacturer (name) VALUES(?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, manufacturer.getName());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            manufacturer.setId(id);
            return manufacturer;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
    @AutoConnection
    @Override
    public void update(Manufacturer manufacturer) throws RepositoryException {
        String query = "UPDATE manufacturer SET name=? where id=?";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, manufacturer.getName());
            ps.setInt(2, manufacturer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
    @AutoConnection
    @Override
    public void delete(Manufacturer manufacturer) throws RepositoryException {
        String query = "DELETE FROM manufacturer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, manufacturer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }
}
