package org.example;

import dto.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DatabaseQueryService {

    private final DataSource dataSource;

    public DatabaseQueryService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String readSqlFile(String fileName) throws Exception {
        return Files.readString(Path.of("sql/" + fileName));
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() throws Exception {
        String sql = readSqlFile("find_max_projects_client_db.sql");
        List<MaxProjectCountClient> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(new MaxProjectCountClient(
                        rs.getString("name"),
                        rs.getInt("project_count")
                ));
            }
        }
        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws Exception {
        String sql = readSqlFile("find_max_salary_worker.sql");
        List<MaxSalaryWorker> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(new MaxSalaryWorker(
                        rs.getString("name"),
                        rs.getInt("salary")
                ));
            }
        }
        return result;
    }

    public List<LongestProject> findLongestProject() throws Exception {
        String sql = readSqlFile("find_longest_project.sql");
        List<LongestProject> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(new LongestProject(
                        rs.getString("name"),
                        rs.getInt("month_count")
                ));
            }
        }
        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws Exception {
        String sql = readSqlFile("find_youngest_eldest_workers.sql");
        List<YoungestEldestWorker> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(new YoungestEldestWorker(
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDate("birthday").toLocalDate()
                ));
            }
        }
        return result;
    }

    public List<ProjectPrice> printProjectPrices() throws Exception {
        String sql = readSqlFile("print_project_prices.sql");
        List<ProjectPrice> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(new ProjectPrice(
                        rs.getString("name"),
                        rs.getInt("price")
                ));
            }
        }
        return result;
    }
}
