package basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fileHandler {
    private String filePath;

    public fileHandler(String filePath) {
        this.filePath = filePath;
    }

    // Method to create a new file and write initial CSV data
    public void writeCsvToFile(List<String[]> data) throws IOException {
        File file = new File(filePath);
        file.createNewFile();  // Create the file if it does not exist
        try (FileWriter fw = new FileWriter(file, false);  // false to overwrite
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String[] line : data) {
                bw.write(String.join(",", line));
                bw.newLine();
            }
        }
    }

    // Method to read CSV values from the file into an ArrayList
    public List<String[]> readCsvFromFile() throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        }
        return data;
    }
    
    public String getFilePath() { 
        return filePath; 
    }

    // Method to rewrite CSV data back to the file
    public void rewriteCsvToFile(List<String[]> data) throws IOException {
        writeCsvToFile(data);  // Reuse the writeCsvToFile method
    }

    public static void main(String[] args) {
        fileHandler manager = new fileHandler("test.csv");
        List<String[]> dataToWrite = new ArrayList<>();
        dataToWrite.add(new String[]{"ID", "Name", "Age"});
        dataToWrite.add(new String[]{"1", "Alice", "30"});
        dataToWrite.add(new String[]{"2", "Bob", "25"});
        dataToWrite.add(new String[]{"3", "Charlie", "35"});

        try {
            manager.writeCsvToFile(dataToWrite);
            List<String[]> readData = manager.readCsvFromFile();
            System.out.println("CSV Data Read from File:");
            for (String[] line : readData) {
                System.out.println(String.join(", ", line));
            }
            manager.rewriteCsvToFile(readData);
            System.out.println(manager.getFilePath()); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
