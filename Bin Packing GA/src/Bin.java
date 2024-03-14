import java.util.ArrayList;

public class Bin {
    final int CAPACITY = 10;
    public int totalWeight = 0;
    ArrayList<Package> packages = new ArrayList<Package>();

    public Bin() {

    }

    public Bin(ArrayList<Package> packages) {
        this.packages = packages;
        updateTotalWeight();
    }

    public int getFreeSpace() {
        updateTotalWeight();
        return CAPACITY - totalWeight;
    }

    public void updateTotalWeight() {
        totalWeight = 0;
        for (Package p: packages) {
            totalWeight += p.weight;
        }
    }

    public void addPackage(Package p) {
        packages.add(p);
        updateTotalWeight();
    }

    public void addPackage(ArrayList<Package> packages) {
        this.packages.addAll(packages);
        updateTotalWeight();
    }

    public String toString() {
        String listPackages = "";
        for(Package p : packages) {
            listPackages += (p + "\n");
        }
        return listPackages;
    }
}
