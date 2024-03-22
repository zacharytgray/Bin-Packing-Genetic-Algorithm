package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> packages = new ArrayList<Integer>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Bin Packing GA/Datasets/WainwrightDataSets/100BinProblem.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] vals = line.split(",");
                for(String weight : vals) {
                    packages.add(Integer.parseInt(weight));
                }
            }
            reader.close();

        }
        catch(IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        Generation g = new Generation(packages);

    }
}