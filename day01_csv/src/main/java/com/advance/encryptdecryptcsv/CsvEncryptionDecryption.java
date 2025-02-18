package com.advance.encryptdecryptcsv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CsvEncryptionDecryption {

    // Method to generate a secret key for AES encryption
    private static SecretKey generateSecretKey() throws Exception {
        // Using a key generator to generate the AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // AES-128
        return keyGenerator.generateKey();
    }

    // Method to encrypt a text using AES
    private static String encrypt(String data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedData);  // Return as Base64 string
    }

    // Method to decrypt a text using AES
    private static String decrypt(String encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    // Method to write data to CSV with encrypted sensitive fields
    public static void writeEncryptedCsv(String fileName, SecretKey secretKey) {
        try {
            // Sample data (including sensitive fields like salary and email)
            String[][] students = {
                    {"1", "Raj Sharma", "21", "A", "Computer Science", "raj@example.com", "75000"},
                    {"2", "Gagan Patel", "22", "B", "Mathematics", "gagan@example.com", "68000"},
                    {"3", "Shubham Gupta", "20", "A", "Physics", "shubham@example.com", "54000"}
            };

            // Prepare CSV writer
            FileWriter fileWriter = new FileWriter(fileName);
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("ID", "Name", "Age", "Grade", "Department", "Email", "Salary"));

            // Write data row by row
            for (String[] student : students) {
                // Encrypt sensitive fields (Email and Salary)
                String encryptedEmail = encrypt(student[5], secretKey);
                String encryptedSalary = encrypt(student[6], secretKey);

                // Write the encrypted data to the CSV
                csvPrinter.printRecord(student[0], student[1], student[2], student[3], student[4], encryptedEmail, encryptedSalary);
            }

            csvPrinter.flush();
            csvPrinter.close();
            System.out.println("CSV with encrypted sensitive fields written successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to read CSV and decrypt sensitive fields
    public static void readDecryptedCsv(String fileName, SecretKey secretKey) {
        try {
            // Read the CSV file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(bufferedReader);

            // Loop through the records and decrypt sensitive fields
            for (CSVRecord record : records) {
                String id = record.get("ID");
                String name = record.get("Name");
                String age = record.get("Age");
                String grade = record.get("Grade");
                String department = record.get("Department");
                String encryptedEmail = record.get("Email");
                String encryptedSalary = record.get("Salary");

                // Decrypt sensitive fields
                String decryptedEmail = decrypt(encryptedEmail, secretKey);
                String decryptedSalary = decrypt(encryptedSalary, secretKey);

                // Print the record
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade + ", Department: " + department + ", Email: " + decryptedEmail + ", Salary: " + decryptedSalary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Generate a secret key
            SecretKey secretKey = generateSecretKey();

            // Encrypt and write to CSV
            writeEncryptedCsv("src/main/java/com/advance/encryptdecryptcsv/students_encrypted.csv", secretKey);

            // Decrypt and read from CSV
            readDecryptedCsv("src/main/java/com/advance/encryptdecryptcsv/students_encrypted.csv", secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
