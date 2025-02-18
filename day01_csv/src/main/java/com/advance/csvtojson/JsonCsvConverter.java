package com.advance.csvtojson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class JsonCsvConverter {

    // Method to convert JSON to CSV
    public static void jsonToCsv(String jsonFile, String csvFile) {
        try {
            // Reading JSON file
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();

            // Parsing JSON content
            JSONArray jsonArray = new JSONArray(jsonContent.toString());

            // Writing to CSV
            FileWriter writer = new FileWriter(csvFile);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Age", "Grade", "Department"));

            // Loop through JSON array and write records to CSV
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject student = jsonArray.getJSONObject(i);
                csvPrinter.printRecord(
                        student.getInt("id"),
                        student.getString("name"),
                        student.getInt("age"),
                        student.getString("grade"),
                        student.getString("department")
                );
            }

            csvPrinter.flush();
            csvPrinter.close();
            System.out.println("CSV file created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to convert CSV to JSON
    public static void csvToJson(String csvFile, String jsonFile) {
        try {
            // Reading CSV file
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String line;
            List<JSONObject> jsonList = new ArrayList<>();
            boolean isHeader = true;
            String[] headers = null;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    headers = line.split(",");
                    isHeader = false;
                    continue;
                }

                String[] values = line.split(",");
                JSONObject studentJson = new JSONObject();
                for (int i = 0; i < headers.length; i++) {
                    studentJson.put(headers[i], values[i]);
                }
                jsonList.add(studentJson);
            }

            // Writing to JSON file
            JSONArray jsonArray = new JSONArray(jsonList);
            FileWriter writer = new FileWriter(jsonFile);
            writer.write(jsonArray.toString(4)); // Pretty print with an indentation of 4 spaces
            writer.close();
            System.out.println("JSON file created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Convert JSON to CSV
        jsonToCsv("src/main/java/com/advance/csvtojson/student.json", "src/main/java/com/advance/csvtojson/student.csv");

        // Convert CSV back to JSON
        csvToJson("src/main/java/com/advance/csvtojson/student.csv", "src/main/java/com/advance/csvtojson/students_converted.json");
    }
}
