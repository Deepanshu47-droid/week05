package com.advance.writecsvaftervalidation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvDataWriter {
    private static final String OUTPUT_FILE = "src/main/java/com/advance/writecsvaftervalidation/valid_data.csv";

    // Method to write valid data into a new CSV file
    public static void writeValidData(List<String> validData) {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            for (String row : validData) {
                writer.write(row + "\n");
            }
            System.out.println("Valid data written to " + OUTPUT_FILE);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
