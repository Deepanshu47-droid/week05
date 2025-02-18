package com.advance.detectduplicates;

import java.io.*;
import java.util.*;

public class DetectDuplicates {
    public static void main(String[] args) {
        // Initializing a list to hold the records from the CSV
        List<String[]> records = new ArrayList<>();

        // Using a HashSet to track unique IDs
        Set<String> uniqueIds = new HashSet<>();

        // Using a HashSet to track duplicate IDs
        Set<String> duplicateIds = new HashSet<>();

        // Reading the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/advance/detectduplicates/student.csv"))) {
            String line;
            // Skipping the header if present
            br.readLine(); // This reads the header line (if there's one)

            // Reading each line of the CSV file
            while ((line = br.readLine()) != null) {
                // Splitting the line by commas to get individual fields
                String[] record = line.split(",");

                // Checking if the ID is a duplicate
                String id = record[0];  // Assuming the ID is in the first column

                if (uniqueIds.contains(id)) {
                    // If the ID is already in uniqueIds, it's a duplicate, adding to duplicateIds set
                    duplicateIds.add(id);
                } else {
                    // Otherwise, adding the ID to uniqueIds set
                    uniqueIds.add(id);
                }

                // Adding the record to the records list
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Printing duplicate records
        System.out.println("Duplicate Records:");
        for (String[] record : records) {
            String id = record[0];
            // Checking if the current record's ID is in the duplicate set
            if (duplicateIds.contains(id)) {
                // Printing the duplicate record
                System.out.println(Arrays.toString(record));
            }
        }
    }
}
