package basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
        this.ensureFileExists();
    }
    
    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile(); // Create the file if it does not exist
                System.out.println("File created: " + filePath);
            } catch (IOException e) {
                System.err.println("Unable to create file: " + filePath);
                e.printStackTrace();
            }
        }
    }
    
    // Method to create a new file and write initial CSV data as single strings per line
    public void writeCsvToFile(List<String> data) throws IOException {
        File file = new File(filePath);
        file.createNewFile();  // Create the file if it does not exist
        try (FileWriter fw = new FileWriter(file, false);  // false to overwrite
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    // Method to read CSV values from the file into an ArrayList of single strings per line
    public List<String> readCsvFromFile() throws IOException {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        }
        return data;
    }

    // Method to rewrite CSV data back to the file
    public void rewriteCsvToFile(List<String> data) throws IOException {
        writeCsvToFile(data);  // Reuse the writeCsvToFile method
    }

    public static void main(String[] args) {
        FileHandler manager = new FileHandler("test.csv");
        List<String> dataToWrite = new ArrayList<>();
        dataToWrite.add("ID,Name,Age");
        dataToWrite.add("1,Alice,30");
        dataToWrite.add("2,Bob,25");
        dataToWrite.add("3,Charlie,35");

        try {
            manager.writeCsvToFile(dataToWrite);
            List<String> readData = manager.readCsvFromFile();
            System.out.println("CSV Data Read from File:");
            for (String line : readData) {
                System.out.println(line);
            }
            manager.rewriteCsvToFile(readData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
