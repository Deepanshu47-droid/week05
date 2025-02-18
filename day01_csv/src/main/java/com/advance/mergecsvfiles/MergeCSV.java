package com.advance.mergecsvfiles;

import java.io.*;
import java.util.*;

public class MergeCSV {
    public static void main(String[] args) {
        String file1 = "src/main/java/com/advance/mergecsvfiles/student1.csv";
        String file2 = "src/main/java/com/advance/mergecsvfiles/student2.csv";
        String outputFile = "src/main/java/com/advance/mergecsvfiles/merged_students.csv";

        Map<String, String[]> studentData = new HashMap<>();

        // Reading students1.csv (ID, Name, Age)
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                studentData.put(parts[0], new String[]{parts[1], parts[2]});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading students2.csv (ID, Marks, Grade) and merging
        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (studentData.containsKey(parts[0])) {
                    String[] details = studentData.get(parts[0]);
                    studentData.put(parts[0], new String[]{details[0], details[1], parts[1], parts[2]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Writing merged data to a new CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("ID,Name,Age,Marks,Grade\n"); // Header
            for (String id : studentData.keySet()) {
                String[] details = studentData.get(id);
                bw.write(id + "," + String.join(",", details) + "\n");
            }
            System.out.println("Merged file created successfully: " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
