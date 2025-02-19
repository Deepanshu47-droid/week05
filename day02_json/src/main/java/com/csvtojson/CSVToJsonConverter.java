package com.csvtojson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVToJsonConverter {
    public static void main(String[] args) throws IOException {
        String csvFile = "src/main/java/com/csvtojson/data.csv";
        List<Map<String, String>> data = readCSV(csvFile);

        // Convert to JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

        // Print JSON
        System.out.println(jsonOutput);

        // Save JSON to file (Optional)
        Files.write(Paths.get("src/main/java/com/csvtojson/output.json"), jsonOutput.getBytes());
    }

    private static List<Map<String, String>> readCSV(String filePath) throws IOException {
        List<Map<String, String>> records = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        if (lines.isEmpty()) return records; // Return empty list if CSV is empty

        String[] headers = lines.get(0).split(","); // Read headers (first row)

        for (int i = 1; i < lines.size(); i++) { // Read data rows
            String[] values = lines.get(i).split(",");
            Map<String, String> record = new LinkedHashMap<>();

            for (int j = 0; j < headers.length; j++) {
                record.put(headers[j].trim(), values[j].trim());
            }

            records.add(record);
        }
        return records;
    }
}
