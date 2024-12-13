import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;




public class DataSaver {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean continueEntering = true;


        while (continueEntering) {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter first Name: ");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name: ");
            String email = SafeInput.getNonZeroLenString(in, "Enter Email: ");
            int yearOfBirth = SafeInput.getRangedInt(in,"Enter Year of Birth (e.g. 1990): ", 1000, 9999);


            String idNumber = String.format("%06d", records.size() + 1);


            String record = firstName + ", " + lastName + ", " + idNumber + ", " + email + ", " + yearOfBirth;
            records.add(record);


            continueEntering = SafeInput.getYNConfirm(in,"Would you like to enter another record?");
        }


        String fileName = SafeInput.getNonZeroLenString(in,"Enter the file name (with .csv extension): ");


        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/" + fileName))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}