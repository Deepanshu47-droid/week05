package com.basic.writedatatocsvfile;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvRecordWriter {

    public static void main(String[] args) throws IOException {

        //Creating csv writer to write into csv file
        CSVWriter writer = new CSVWriter(new FileWriter("src/main/java/com/basic/writedatatocsvfile/EmployeeRecords.csv"));

        //Creating a 2-d array to store records to be written in csv file
        String[][] records = {
                {"ID", "Name", "Department", "Salary"},
                {"1", "Deepanshu", "Development", "650000"},
                {"2", "Purvansh", "HR", "87659"},
                {"3", "Raj", "HR", "87868"},
                {"4", "Yogesh", "HR", "778797"}
        };
        for(String[] record : records) {
            writer.writeNext(record);
        }

        System.out.println("All records successfully written to csv file EmployeeRecord.csv......");
        //Closing the writer
        writer.close();
    }
}
