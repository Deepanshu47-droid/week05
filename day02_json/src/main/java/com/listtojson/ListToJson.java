package com.listtojson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ListToJson {
    public static void main(String[] args) {
        try {
            // Create a list of Student objects
            List<Student> students = Arrays.asList(
                    new Student("Deepanshu", 23, Arrays.asList("Math", "Science", "English")),
                    new Student("Gagan", 22, Arrays.asList("Physics", "Chemistry", "Biology"))
            );

            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print JSON

            // Convert List to JSON Array
            String jsonArray = objectMapper.writeValueAsString(students);

            //Writing list in json file
            objectMapper.writeValue(new File("src/main/java/com/listtojson/output.json"), students);
            // Print JSON Array
            System.out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
