package com.advance.csvtojavaobject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvToStudentParser {
    public static List<Student> readStudentsFromCSV(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    Student student = new Student(
                            Integer.parseInt(data[0].trim()),
                            data[1].trim(),
                            data[2].trim(),
                            Double.parseDouble(data[3].trim())
                    );
                    students.add(student);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return students;
    }
}
