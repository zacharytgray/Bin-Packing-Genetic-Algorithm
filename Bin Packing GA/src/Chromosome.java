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




    public ArrayList<Integer> getPackages() {
        ArrayList<Integer> packages = new ArrayList<Integer>();
        for (Bin b:
             bins) {
            packages.addAll(b.packages);
        }

        return packages;
    }



}
