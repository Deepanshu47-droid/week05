package com.jsonreader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import java.util.Iterator;

public class JsonFileReader {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into an array of JsonNodes
            JsonNode rootNode = objectMapper.readTree(new File("src/main/java/com/jsonreader/data.json"));

            // Iterate over each JSON object
            for (JsonNode node : rootNode) {
                // Check and extract 'name'
                if (node.has("name")) {
                    System.out.println("Name: " + node.get("name").asText());
                } else {
                    System.out.println("Name: Not Available");
                }

                // Check and extract 'email'
                if (node.has("email")) {
                    System.out.println("Email: " + node.get("email").asText());
                } else {
                    System.out.println("Email: Not Available");
                }

                // Check and extract 'skills'
                if (node.has("skills")) {
                    System.out.print("Skills: ");
                    JsonNode skillsNode = node.get("skills");

                    if (skillsNode.isArray()) {
                        Iterator<JsonNode> iterator = skillsNode.elements();
                        while (iterator.hasNext()) {
                            System.out.print(iterator.next().asText() + " ");
                        }
                    }
                    System.out.println();
                } else {
                    System.out.println("Skills: Not Available");
                }

                System.out.println("-----------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//-------------FOR SINGLE ENTRY---------------------------------------
/*
public class JsonFileReader {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode
            JsonNode rootNode = objectMapper.readTree(new File("src/main/java/com/jsonreader/data.json"));

            // Extract specific fields
            String name = rootNode.get("name").asText();
            String email = rootNode.get("email").asText();

            // Print extracted values
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
