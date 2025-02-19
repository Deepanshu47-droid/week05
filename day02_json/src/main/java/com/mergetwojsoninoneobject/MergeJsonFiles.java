package com.mergetwojsoninoneobject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class MergeJsonFiles {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON files
            JsonNode json1 = objectMapper.readTree(new File("src/main/java/com/mergetwojsoninoneobject/file1.json"));
            JsonNode json2 = objectMapper.readTree(new File("src/main/java/com/mergetwojsoninoneobject/file2.json"));

            // Merge JSON objects
            JsonNode mergedJson = mergeJson(json1, json2);

            // Print merged JSON
            System.out.println("Merged JSON:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson));

            // Write to a new JSON file
            objectMapper.writeValue(new File("src/main/java/com/mergetwojsoninoneobject/merged.json"), mergedJson);
            System.out.println("Merged JSON saved to merged.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonNode mergeJson(JsonNode mainNode, JsonNode updateNode) {
        if (!mainNode.isObject() || !updateNode.isObject()) {
            return mainNode; // If not objects, return as is
        }

        // Merge two JSON objects
        for (Iterator<Map.Entry<String, JsonNode>> it = updateNode.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            ((com.fasterxml.jackson.databind.node.ObjectNode) mainNode).set(entry.getKey(), entry.getValue());
        }

        return mainNode;
    }
}
