import javax.swing.*;
import java.io.*;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser("src");
        fileChooser.setDialogTitle("Select a Text File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int userChoice = fileChooser.showOpenDialog(null);

        if (userChoice == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            processFile(selectedFile);
        } else {
            System.out.println("No file selected. Exiting program.");
        }
    }

    private static void processFile(File file) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lineCount++;
                wordCount += countWords(line);
                charCount += line.length();
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        printSummaryReport(file.getName(), lineCount, wordCount, charCount);
    }

    private static int countWords(String line) {
        String[] words = line.trim().split("\\s+");
        return words.length;
    }

    private static void printSummaryReport(String fileName, int lineCount, int wordCount, int charCount) {
        System.out.println("\nSummary Report:");
        System.out.println("File name: " + fileName);
        System.out.println("Number of lines: " + lineCount);
        System.out.println("Number of words: " + wordCount);
        System.out.println("Number of characters: " + charCount);
    }
}