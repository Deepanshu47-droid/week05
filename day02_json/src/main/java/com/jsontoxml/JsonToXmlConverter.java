package com.jsontoxml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class JsonToXmlConverter {
    public static void main(String[] args) {
        ObjectMapper jsonMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();

        try {
            // Read JSON file
            JsonNode jsonNode = jsonMapper.readTree(new File("src/main/java/com/jsontoxml/data.json"));

            // Convert JSON to XML
            String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            // Print XML output
            System.out.println("Converted XML:\n" + xmlOutput);

            // Write XML to file
            xmlMapper.writeValue(new File("src/main/java/com/jsontoxml/output.xml"), jsonNode);
            System.out.println("XML saved to output.xml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
