package com.pasrseandfilterjson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class FilterStudents {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into List<Student>
            List<Student> students = objectMapper.readValue(new File("src/main/java/com/pasrseandfilterjson/students.json"), new TypeReference<List<Student>>() {});

            // Filter students where age > 25
            List<Student> filteredStudents = students.stream()
                    .filter(student -> student.getAge() > 25)
                    .collect(Collectors.toList());

            // Print the filtered students
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(System.out, filteredStudents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

