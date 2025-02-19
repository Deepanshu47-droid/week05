package com.createjsonobject;
import org.json.JSONArray;
import org.json.JSONObject;

public class StudentJson {
    public static JSONObject createStudentJson() {
        // Creating JSON object for Student
        JSONObject student = new JSONObject();
        student.put("name", "Deepanshu");
        student.put("age", 23);

        // Creating JSON array for subjects
        JSONArray subjects = new JSONArray();
        subjects.put("Math");
        subjects.put("Physics");
        subjects.put("Computer Science");

        // Adding subjects array to student object
        student.put("subjects", subjects);

        return student;
    }

    public static String getPrettyJson(JSONObject jsonObject) {
        return jsonObject.toString(4); // Pretty print with 4 spaces indentation
    }
}
