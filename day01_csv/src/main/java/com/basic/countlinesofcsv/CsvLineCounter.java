package com.basic.countlinesofcsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvLineCounter {

    public static void main(String[] args) throws IOException, CsvException {

        //Creating csv file reader
        CSVReader reader = new CSVReader(new FileReader("src/main/java/com/basic/countlinesofcsv/Student.csv"));

        //Skipping the header record
        reader.readNext();

        //Reading records and storing into a list
        List<String[]> records = reader.readAll();

        //Creating variable count to store number of records
        int count = records.size();

        //Printing the count
        System.out.println("Number of records is " + count);
    }
}
