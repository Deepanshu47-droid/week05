package com.pasrseandfilterjson;

import java.util.List;

public class Student {
    private String name;
    private int age;
    private List<String> subjects;

    // Default constructor
    public Student() {}

    // Constructor
    public Student(String name, int age, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getSubjects() { return subjects; }
}
