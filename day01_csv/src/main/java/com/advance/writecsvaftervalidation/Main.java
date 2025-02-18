package com.advance.writecsvaftervalidation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String INPUT_FILE = "src/main/java/com/advance/writecsvaftervalidation/data.csv";

    public static void main(String[] args) {
        List<String> validData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) { // Keep the header row
                    validData.add(line);
                    firstLine = false;
                    continue;
                }

                String[] columns = line.split(",");
                if (columns.length < 4) {
                    System.out.println("Invalid row (missing columns): " + line);
                    continue;
                }

                String email = columns[2].replace("\"", "").trim();  // Removing extra quotes
                String phone = columns[3].replace("\"", "").trim();

                if (!DataValidation.isValidEmail(email)) {
                    System.out.println("Invalid email: " + email + " in row: " + line);
                    continue;
                }

                if (!DataValidation.isValidPhone(phone)) {
                    System.out.println("Invalid phone number: " + phone + " in row: " + line);
                    continue;
                }

                validData.add(line); // Adding valid data
            }

            CsvDataWriter.writeValidData(validData); // Writing valid data to new CSV

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

