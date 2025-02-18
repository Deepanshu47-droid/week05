package com.advance.csvtojavaobject;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/advance/csvtojavaobject/student.csv";
        List<Student> students = CsvToStudentParser.readStudentsFromCSV(filePath);
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
