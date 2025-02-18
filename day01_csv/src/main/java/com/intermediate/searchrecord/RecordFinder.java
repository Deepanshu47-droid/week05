package com.intermediate.searchrecord;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RecordFinder {

    public static void main(String[] args) throws IOException, CsvValidationException {
        //Creating csv file reader
        CSVReader reader = new CSVReader(new FileReader("src/main/java/com/intermediate/searchrecord/EmployeeRecords.csv"));

        //Creating variable EmployeeToFind to store employee name to be searched
        String employeeToFind = "Deepanshu";
        //Creating array to store record
        String[] record;

        //Skipping the header record
        reader.readNext();

        //Reading records from csv file
        while((record = reader.readNext()) != null) {

            //Printing department and salary if employee name is deepanshu
            if (record[1].equalsIgnoreCase(employeeToFind)) {
                System.out.println("Employee found......");
                System.out.println("Name: " + record[1]);
                System.out.println("Department: " + record[2]);
                System.out.println("Salary: " + record[3]);
                System.out.println("------------------------------");
            }

        }
        System.out.println("File readed successfully......");
    }
}
