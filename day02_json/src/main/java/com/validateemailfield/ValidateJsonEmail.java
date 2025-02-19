package com.validateemailfield;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileInputStream;

public class ValidateJsonEmail {
    public static void main(String[] args) {
        try {
            // Load JSON Schema
            FileInputStream schemaStream = new FileInputStream("src/main/java/com/validateemailfield/email-schema.json");
            JSONObject schemaObject = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(schemaObject);

            // Load JSON Data
            FileInputStream jsonStream = new FileInputStream("src/main/java/com/validateemailfield/user.json");
            JSONObject jsonData = new JSONObject(new JSONTokener(jsonStream));

            // Validate JSON against schema
            schema.validate(jsonData);

            System.out.println("JSON is valid!");

        } catch (Exception e) {
            System.out.println("Invalid JSON: " + e.getMessage());
        }
    }
}
