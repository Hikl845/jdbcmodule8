package org.example;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final DataSource dbSource;

    public ClientServiceImpl(DataSource dbSource) {
        this.dbSource = dbSource;
    }

    @Override
    public long create(String name) throws SQLException {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        String trimmedName = name.trim();
        if (trimmedName.length() < 2 || trimmedName.length() > 1000) {
            throw new IllegalArgumentException("Name must be between 2 and 1000 characters");
        }

        String sql = "INSERT INTO client (name) VALUES (?)";
        try (Connection conn = dbSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, trimmedName);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new SQLException("Failed to retrieve generated ID");
                }
            }
        }
    }

    @Override
    public String getById(long id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }

        String sql = "SELECT name FROM client WHERE id = ?";
        try (Connection conn = dbSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void setName(long id, String name) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        String trimmedName = name.trim();
        if (trimmedName.length() < 2 || trimmedName.length() > 1000) {
            throw new IllegalArgumentException("Name must be between 2 and 1000 characters");
        }

        String sql = "UPDATE client SET name = ? WHERE id = ?";
        try (Connection conn = dbSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trimmedName);
            ps.setLong(2, id);

            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new SQLException("Client with ID " + id + " not found");
            }
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }

        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection conn = dbSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int deleted = ps.executeUpdate();
            if (deleted == 0) {
                throw new SQLException("Client with ID " + id + " not found");
            }
        }
    }

    @Override
    public List<CLient> listAll() throws SQLException {
        List<CLient> clients = new ArrayList<>();
        String sql = "SELECT id, name FROM client";

        try (Connection conn = dbSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clients.add(new CLient(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
        }
        return clients;
    }
}

