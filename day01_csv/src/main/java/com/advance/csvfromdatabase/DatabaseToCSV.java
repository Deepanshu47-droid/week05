package com.advance.csvfromdatabase;

import java.io.*;
import java.sql.*;

public class DatabaseToCSV {
    public static void main(String[] args) {
        // Database connection variables
        String jdbcUrl = "jdbc:mysql://localhost:3306/yourDatabase"; //write your's jdbc url
        String username = "yourUsername"; //write your username
        String password = "yourPassword";//write your password

        // SQL query to fetch employee records
        String query = "SELECT employee_id, name, department, salary FROM employees";

        // Output CSV file
        String outputFile = "employee_report.csv";

        // Establishing the database connection
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            // Writing the header to the CSV
            writer.write("Employee ID,Name,Department,Salary");
            writer.newLine();

            // Fetching records from the ResultSet and writing them to CSV
            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                // Writing each record to the CSV file
                writer.write(employeeId + "," + name + "," + department + "," + salary);
                writer.newLine();
            }

            System.out.println("CSV report generated successfully!");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

