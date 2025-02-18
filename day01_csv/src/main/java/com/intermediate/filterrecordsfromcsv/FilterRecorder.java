package com.intermediate.filterrecordsfromcsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class FilterRecorder {
    public static void main(String[] args) throws IOException, CsvValidationException {

        //Creating csv file reader
        CSVReader reader = new CSVReader(new FileReader("src/main/java/com/intermediate/filterrecordsfromcsv/Student.csv"));

        //Creating array to store record
        String[] record;

        //Skipping the header record
        reader.readNext();

        System.out.println("Below is the filtered students......");
        //Reading records from csv file
        while((record = reader.readNext()) != null) {

            if(Double.parseDouble(record[3]) > 80) {
                //Printing the records
                System.out.println("ID: " + record[0]);
                System.out.println("Name: " + record[1]);
                System.out.println("Age: " + record[2]);
                System.out.println("Marks: " + record[3]);
                System.out.println("------------------------------");
            }
        }
        System.out.println("Successfully readed csv file.....");
    }
}
