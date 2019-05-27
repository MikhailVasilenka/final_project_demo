package by.vasilenka.repository.impl;

import by.vasilenka.domain.Item;
import by.vasilenka.repository.AbstractJdbcRepository;
import by.vasilenka.repository.AutoConnection;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.exception.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ItemRepository extends AbstractJdbcRepository<Item, Integer> implements Repository<Item, Integer> {
    @AutoConnection
    @Override
    public Item add(Item item) throws RepositoryException {
        String query = "INSERT INTO drug_order_details (drug_amount,drug_id,drug_order_id) " +
                "VALUES(?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, item.getAmount());
            ps.setInt(2, item.getDrug().getId());
            ps.setInt(3, item.getOrderId());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            item.setId(id);
            return item;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }

    @AutoConnection
    @Override
    public void update(Item item) throws RepositoryException {
        String query = "UPDATE  drug_order_details SET drug_amount=?,drug_id=?,drug_order_id=? where id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, item.getAmount());
            ps.setInt(2, item.getDrug().getId());
            ps.setInt(3, item.getOrderId());
            ps.setInt(4, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }

    @AutoConnection
    @Override
    public void delete(Item item) throws RepositoryException {
        String query = "DELETE FROM drug_order_details WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

}
