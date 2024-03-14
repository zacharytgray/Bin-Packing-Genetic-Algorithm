import java.util.ArrayList;

public class Chromosome {
    int fitness;
    ArrayList<Bin> bins = new ArrayList<Bin>();

    public Chromosome(){

    }

    public Chromosome(ArrayList<Bin> bins) {
        this.bins = bins;
    }

    public int getLength() {
        return bins.size();
    }

    public void addBin(Bin b) {
        bins.add(b);
    }




    public ArrayList<Package> getPackages() {
        ArrayList<Package> packages = new ArrayList<Package>();
        for (Bin b:
             bins) {
            packages.addAll(b.packages);
        }

        return packages;
    }



}
