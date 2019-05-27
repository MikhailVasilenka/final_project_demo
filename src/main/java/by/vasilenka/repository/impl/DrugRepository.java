package by.vasilenka.repository.impl;

import by.vasilenka.domain.Drug;
import by.vasilenka.repository.AbstractJdbcRepository;
import by.vasilenka.repository.AutoConnection;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.exception.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DrugRepository extends AbstractJdbcRepository<Drug, Integer> implements Repository<Drug, Integer> {
    @AutoConnection
    @Override
    public Drug add(Drug drug) throws RepositoryException {
        String query = "INSERT INTO drug (name,is_prescription_required,price,release_form_id,manufacturer_id,available_amount) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, drug.getName());
            ps.setBoolean(2, drug.getIsPrescriptionRequired());
            ps.setInt(3, drug.getPrice());
            ps.setInt(4, drug.getReleaseForm().getId());
            ps.setInt(5, drug.getManufacturer().getId());
            ps.setInt(6, drug.getAvailableAmount());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            drug.setId(id);
            return drug;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }

    @AutoConnection
    @Override
    public void update(Drug drug) throws RepositoryException {

        String query = "UPDATE drug " +
                "SET name=?, is_prescription_required=?, price=?, available_amount=? " +
                "WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, drug.getName());
            ps.setBoolean(2, drug.getIsPrescriptionRequired());
            ps.setInt(3, drug.getPrice());
            ps.setInt(4, drug.getAvailableAmount());
            ps.setInt(5, drug.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

    @AutoConnection
    @Override
    public void delete(Drug drug) throws RepositoryException {

        String query = "DELETE FROM drug WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, drug.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

}