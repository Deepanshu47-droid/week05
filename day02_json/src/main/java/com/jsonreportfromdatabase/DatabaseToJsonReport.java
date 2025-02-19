package com.jsonreportfromdatabase;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.*;
import java.util.*;
import java.nio.file.*;

public class DatabaseToJsonReport {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/your_database"; // Change database URL
        String username = "root"; // Change username
        String password = "password"; // Change password

        String query = "SELECT id, name, age, city FROM users";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            List<Map<String, Object>> records = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> record = new LinkedHashMap<>();
                record.put("id", rs.getInt("id"));
                record.put("name", rs.getString("name"));
                record.put("age", rs.getInt("age"));
                record.put("city", rs.getString("city"));
                records.add(record);
            }

            // Convert list to JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);

            // Print JSON
            System.out.println(jsonOutput);

            // Save JSON to file
            Files.write(Paths.get("database_report.json"), jsonOutput.getBytes());

            System.out.println("JSON report generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

