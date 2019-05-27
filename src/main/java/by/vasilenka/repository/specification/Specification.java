package by.vasilenka.repository.specification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface Specification<T> {
    List<T> get(T object, Connection connection) throws SQLException;
}
