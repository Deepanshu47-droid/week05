package com.advance.csvtojavaobject;

public class Student {
    private int id;
    private String name;
    private String department;
    private double marks;

    public Student(int id, String name, String department, double marks) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", marks=" + marks +
                '}';
    }
}
