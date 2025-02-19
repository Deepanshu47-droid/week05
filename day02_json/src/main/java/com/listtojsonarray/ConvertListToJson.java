package com.listtojsonarray;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.io.File;

public class ConvertListToJson {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Create a list of students
            List<Student> students = Arrays.asList(
                    new Student("Deepanshu", 23, Arrays.asList("Math", "Science")),
                    new Student("Gagan", 28, Arrays.asList("Physics", "Chemistry")),
                    new Student("Raj", 30, Arrays.asList("Biology", "English"))
            );

            // Convert the list to JSON and write to file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/java/com/listtojsonarray/students.json"), students);

            // Print JSON output to console
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
            System.out.println(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
