package org.example;
import java.sql.SQLException;
import java.util.List;

public interface ClientService {

    long create(String name) throws SQLException;

    String getById(long id) throws SQLException;

    void setName(long id, String name) throws SQLException;

    void deleteById(long id) throws SQLException;

    List<CLient> listAll() throws SQLException;
}

