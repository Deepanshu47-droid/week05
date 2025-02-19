package com.validatejsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.File;
import java.io.IOException;

public class JsonValidator {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON schema and JSON data
            JsonNode schemaNode = objectMapper.readTree(new File("src/main/java/com/validatejsonschema/schema.json"));
            JsonNode jsonNode = objectMapper.readTree(new File("src/main/java/com/validatejsonschema/data.json"));

            // Create a JSON Schema Validator
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);

            // Validate JSON
            ProcessingReport report = schema.validate(jsonNode);

            // Print validation result
            if (report.isSuccess()) {
                System.out.println(" JSON is valid!");
            } else {
                System.out.println(" JSON validation failed!");
                System.out.println(report);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error in JSON validation: " + e.getMessage());
        }
    }
}
