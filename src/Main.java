import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            NumberFormat number = NumberFormat.getInstance();
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            String line, city, name, maxName = "", minName = "", maxNameSD = "", minNameSD = "";
            String[] parts;
            int count = 0, countSD = 0;
            double sum = 0, max=Double.MIN_VALUE, min=Double.MAX_VALUE, price, sumSD = 0, maxSD=Double.MIN_VALUE, minSD=Double.MAX_VALUE;
            Scanner file = new Scanner(new File("USPizzaDataset.csv"));
            // Skip the heading
            file.nextLine();
            // Loop through the file
            while (file.hasNextLine()) {
                line = file.nextLine();
                // Split the String into parts whenever a comma is preset
                parts = line.split(",");
                name = parts[9];
                price = Double.parseDouble(parts[7]); // Convert into double
                city = parts[2];
                // Add the price to sum
                sum += price;
                count++;
                // Check for min or max
                if (price > max) { max = price; maxName = name; }
                if (price < min) { min = price; minName = name; }

                // Stats for San Diego
                if ("San Diego".equalsIgnoreCase(city)) {
                    sumSD += price;
                    countSD++;

                    if (price < minSD) {minSD = price; minNameSD = name;}
                    if (price > maxSD) {maxSD = price; maxNameSD = name;}
                }
            }
            // Once done, close the file
            // All
            System.out.println("Number of price quotes: " + number.format(count));
            System.out.println("Average Price: " + currency.format(sum/count));
            System.out.println("Lowest priced Pizza is " + currency.format(min) + " at " + minName + ".");
            System.out.println("Highest priced Pizza is " + currency.format(max) + " at " + maxName + ".");
            // San Diego
            System.out.println("Number of price quotes in San Diego: " + number.format(countSD));
            System.out.println("Average Price in San Diego: " + currency.format(sumSD/countSD));
            System.out.println("Lowest priced Pizza in San Diego is " + currency.format(minSD) + " at " + minNameSD + ".");
            System.out.println("Highest priced Pizza in San Diego is " + currency.format(maxSD) + " at " + maxNameSD + ".");
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}