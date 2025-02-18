package com.basic.readcsvfile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CSVPrinter {

    public static void main(String[] args) throws IOException, CsvValidationException {

        //Creating csv file reader
        CSVReader reader = new CSVReader(new FileReader("src/main/java/com/basic/readcsvfile/Student.csv"));

        //Creating array to store record
        String[] line;

        //Skipping the header record
        reader.readNext();

        //Reading records from csv file
        while((line = reader.readNext()) != null) {
            System.out.println("ID: " + line[0]);
            System.out.println("Name: " + line[1]);
            System.out.println("Age: " + line[2]);
            System.out.println("Marks: " + line[3]);
            System.out.println("------------------------------");
        }
        System.out.println("Successfully readed csv file.....");
    }
}
