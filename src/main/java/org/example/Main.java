package org.example;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        // 1. Створюємо DataSource
        DataSource dataSource = createDataSource();

        // 2. Створюємо таблицю (для тесту)
        initDatabase(dataSource);

        // 3. Створюємо сервіс
        ClientService clientService = new ClientServiceImpl(dataSource);

        try {
            // 4. Використовуємо сервіс
            long id = clientService.create("John Doe");
            System.out.println("Created client with id: " + id);

            String name = clientService.getById(id);
            System.out.println("Client name: " + name);

            clientService.setName(id, "Jane Doe");
            System.out.println("Updated name: " + clientService.getById(id));

            clientService.deleteById(id);
            System.out.println("Client deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DataSource createDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        ds.setUser("sa");
        ds.setPassword("");
        return ds;
    }

    private static void initDatabase(DataSource dataSource) {
        String sql =
                "CREATE TABLE client (" +
                        " id IDENTITY PRIMARY KEY," +
                        " name VARCHAR(1000) NOT NULL" +
                        ")";


        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
