package Main;

import java.util.ArrayList;

public class Bin {
    final int CAPACITY = 20;
    public int totalWeight = 0;
    ArrayList<Integer> packages = new ArrayList<Integer>();

    public Bin() {

    }

    public Bin(ArrayList<Integer> packages) {
        this.packages = packages;
        updateTotalWeight();
    }

    public int getFreeSpace() {
        updateTotalWeight();
        return CAPACITY - totalWeight;
    }

    public void updateTotalWeight() {
        totalWeight = 0;
        for (int p: packages) {
            totalWeight += p;
        }
    }

    public void addPackage(Integer p) {
        packages.add(p);
        updateTotalWeight();
    }

    public void addPackage(ArrayList<Integer> packages) {
        this.packages.addAll(packages);
        updateTotalWeight();
    }

    public String toString() {
        String listPackages = "";
        for(Integer p : packages) {
            listPackages += (p + ", ");
        }
        int len = listPackages.length();
        return listPackages.substring(0, len-2);
    }
}
