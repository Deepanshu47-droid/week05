package com.filterjsondata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;

public class FilterJson {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode
            JsonNode rootNode = objectMapper.readTree(new File("src/main/java/com/filterjsondata/users.json"));

            // Iterate through array and filter users older than 25
            for (JsonNode userNode : rootNode) {
                int age = userNode.get("age").asInt();
                if (age > 25) {
                    System.out.println(userNode.toPrettyString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
