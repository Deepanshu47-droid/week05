package com.jsonmerger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class JsonMerger {
    public static void main(String[] args) {
        try {
            // Creating ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Reading two JSON files as JsonNode
            JsonNode jsonNode1 = objectMapper.readTree(new File("src/main/java/com/jsonmerger/file1.json"));
            JsonNode jsonNode2 = objectMapper.readTree(new File("src/main/java/com/jsonmerger/file2.json"));

            // Converting JsonNode to ObjectNode for modification
            ObjectNode mergedNode = objectMapper.createObjectNode();
            mergedNode.setAll((ObjectNode) jsonNode1);
            mergedNode.setAll((ObjectNode) jsonNode2);

            // Printing merged JSON
            String mergedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedNode);
            System.out.println(mergedJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
