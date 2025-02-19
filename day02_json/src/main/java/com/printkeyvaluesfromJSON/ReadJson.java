package com.printkeyvaluesfromJSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class ReadJson {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode
            JsonNode rootNode = objectMapper.readTree(new File("src/main/java/com/printkeyvaluesfromJSON/data.json"));

            // Print all keys and values
            printJson(rootNode, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recursive method to print JSON keys and values
    private static void printJson(JsonNode node, String indent) {
        if (node.isObject()) {
            // If node is an object, iterate over its fields
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                System.out.println(indent + field.getKey() + " :");
                printJson(field.getValue(), indent + "  ");
            }
        } else if (node.isArray()) {
            // If node is an array, iterate over elements
            for (JsonNode arrayElement : node) {
                printJson(arrayElement, indent + "  ");
            }
        } else {
            // If node is a value, print it
            System.out.println(indent + node.asText());
        }
    }
}
