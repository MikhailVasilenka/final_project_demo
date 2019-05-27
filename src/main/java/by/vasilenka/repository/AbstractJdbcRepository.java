package by.vasilenka.repository;

import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.specification.Specification;

import java.sql.*;
import java.util.List;

/**
 * Abstract JDBC DAO
 *
 * @param <T>  - Identified entity
 * @param <PK> - Type primary key of entity
 */
public abstract class AbstractJdbcRepository<T extends Identified<PK>, PK extends Number> implements Repository<T, PK> {
    protected Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @AutoConnection
    @Override
    public List<T> getQuery(T obj, Specification<T> spec) throws RepositoryException {
        try {
            return spec.get(obj, connection);
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
}
