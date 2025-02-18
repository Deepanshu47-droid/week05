package com.intermediate.sortemployees;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EmployeeSorter {

    public static void main(String[] args) throws IOException, CsvException {
        //Creating csv file reader
        CSVReader reader = new CSVReader(new FileReader("src/main/java/com/intermediate/sortemployees/EmployeeRecords.csv"));

        //Creating csv file writer
        CSVWriter writer = new CSVWriter(new FileWriter("src/main/java/com/intermediate/sortemployees/sortedEmployeeRecords.csv"));

        //Storing the header record
        String[] header = reader.readNext();


        //Reading records from csv file
        List<String[]> records = reader.readAll();

        //Creating an anonymous comparator object to arrange records in descending order
        Comparator myComparator = new Comparator() {

            @Override
            public int compare(Object object1, Object object2) {

                double salary1 = Double.parseDouble(((String[])object1)[3]);
                double salary2 = Double.parseDouble(((String[])object2)[3]);

                 if(salary1 > salary2) {
                     return -1;
                 } else if(salary1 < salary2) {
                     return 1;
                 } else {
                     return 0;
                 }
             }
        };

        //Sorting the records in descending order using my comparator
        Collections.sort(records, myComparator);

        //Writing the header to new file
        writer.writeNext(header);

        //Writing the sorted records in new file
        for(String[] record : records) {
            writer.writeNext(record);
        }

        //Printing 5 highly paid employees
        System.out.println("5 highest paid employees are......");
        for(int i=0; i<5; i++) {
            System.out.println("ID: " + records.get(i)[0]);
            System.out.println("Name: " + records.get(i)[1]);
            System.out.println("Department: " + records.get(i)[2]);
            System.out.println("Salary: " + records.get(i)[3]);
            System.out.println("----------------------------------------");
        }

        writer.close();

    }

}
