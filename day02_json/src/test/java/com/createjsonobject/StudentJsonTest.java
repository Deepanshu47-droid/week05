package com.createjsonobject;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentJsonTest {

    @Test
    public void testJsonStructure() {
        JSONObject studentJson = StudentJson.createStudentJson();

        // Check if the JSON contains expected keys
        assertTrue(studentJson.has("name"));
        assertTrue(studentJson.has("age"));
        assertTrue(studentJson.has("subjects"));

        // Check values
        assertEquals("Deepanshu", studentJson.getString("name"));
        assertEquals(23, studentJson.getInt("age"));
        assertEquals(3, studentJson.getJSONArray("subjects").length());
    }

    @Test
    public void testPrettyPrint() {
        JSONObject studentJson = StudentJson.createStudentJson();
        String prettyJson = StudentJson.getPrettyJson(studentJson);

        // Check if the pretty-printed JSON contains new lines and indentation
        assertTrue(prettyJson.contains("\n")); // Ensures it's formatted
        assertTrue(prettyJson.contains("    ")); // Check for indentation
    }
}
