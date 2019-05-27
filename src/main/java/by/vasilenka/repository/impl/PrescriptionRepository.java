package by.vasilenka.repository.impl;

import by.vasilenka.domain.Prescription;
import by.vasilenka.repository.AbstractJdbcRepository;
import by.vasilenka.repository.AutoConnection;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.exception.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrescriptionRepository extends AbstractJdbcRepository<Prescription,Integer> implements Repository<Prescription,Integer> {
    @AutoConnection
    @Override
    public Prescription add(Prescription prescription) throws RepositoryException {
        String query = "INSERT INTO prescription (description,issue_date,validity_date,drug_id,doctor_id,user_id) " +
                "VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, prescription.getDescription());
            ps.setDate(2, prescription.getIssueDate());
            ps.setDate(3, prescription.getValidityDate());
            ps.setInt(4, prescription.getDrugId());
            ps.setInt(5, prescription.getDoctorId());
            ps.setInt(6, prescription.getUserId());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            prescription.setId(id);
            return prescription;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
    @AutoConnection
    @Override
    public void update(Prescription prescription) throws RepositoryException {
        String query = "UPDATE  prescription " +
                "SET description=?,issue_date=?,validity_date=?,drug_id=?,doctor_id=?,user_id=? " +
                "where id =? ";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, prescription.getDescription());
            ps.setDate(2, prescription.getIssueDate());
            ps.setDate(3, prescription.getValidityDate());
            ps.setInt(4, prescription.getDrugId());
            ps.setInt(5, prescription.getDoctorId());
            ps.setInt(6, prescription.getUserId());
            ps.setInt(7, prescription.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
    @AutoConnection
    @Override
    public void delete(Prescription prescription) throws RepositoryException {
        String query = "DELETE from prescription where id =? ";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, prescription.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
}
