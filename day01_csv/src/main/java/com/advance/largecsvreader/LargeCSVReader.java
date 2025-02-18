package com.advance.largecsvreader;

import java.io.*;

public class LargeCSVReader {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/advance/largecsvreader/large";  // Replacing with your actual file path
        int batchSize = 100; // Reading in chunks of 100 lines
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skipping the header row

            while ((line = br.readLine()) != null) {
                count++;

                // Processing the line (Here, we're just printing the count)
                System.out.println("Processing Record: " + count);


                if (count % batchSize == 0) {
                    System.out.println("Processed " + count + " records so far...");
                }
            }

            System.out.println("Total Records Processed: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

