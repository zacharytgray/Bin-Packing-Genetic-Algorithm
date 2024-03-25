package CrossoverTechniques;
import java.util.ArrayList;
import java.util.Random;
import Main.Chromosome;

public class Order2 {
    public Chromosome p1;
    public Chromosome p2;
    public Chromosome c1 = new Chromosome();
    public Chromosome c2 = new Chromosome();
    public final int POPULATIONSIZE;

    public final double specialPositionsPercentage = 0.40;
    public int numSpecialPositions;
    public ArrayList<Integer> SpecialIndicesOther = new ArrayList<>(); // list of special indeces for opposite parent
    public ArrayList<Integer> specialIndecesCurrParent = new ArrayList<>();

    public Order2(Chromosome p1, Chromosome p2, int POPULATIONSIZE) {
        this.p1 = p1;
        this.p2 = p2;
        this.POPULATIONSIZE = POPULATIONSIZE;
        for (int i = 0; i < POPULATIONSIZE; i++) { // fill each child array with 0s to start (they will be replaced)
            c1.getPackages().add(0);
            c2.getPackages().add(0);
        }

        doOrder2();
        System.out.println(SpecialIndicesOther);

        System.out.println("C1: " + c1);
        System.out.println("C2: " + c2);
    }

    public void doOrder2() { // PROBLEM: We can have repeat weights in a specific chromosome. Basically, each chromosome is not a set like they are in the example problems on harvey.

        numSpecialPositions = (int) Math.round(specialPositionsPercentage * POPULATIONSIZE);
        int specialIndex;
        for (int i = 0; i < numSpecialPositions; i++) { // creates a list of special indices
            boolean AlreadyExists = true;
            while (AlreadyExists) {
                specialIndex = new Random().nextInt(POPULATIONSIZE);
                if (!SpecialIndicesOther.contains(specialIndex)) { //if specialIndex is already taken, try again until a free one is found.
                    SpecialIndicesOther.add(specialIndex);
                    AlreadyExists = false;
                }
            }
        }


        ArrayList<Integer> specialValues = new ArrayList<>();
        for(Integer i : SpecialIndicesOther) {// creates a list of special values (not indices) used for C1 from P2s values
            specialValues.add(p2.getPackages().get(i));
        }

        // now, we need to fill specialIndecesCurrParent with indices in P1 of objects in specialValues

        for(Integer i: specialValues) {
            int index = p1.getPackages().indexOf(i);
            specialIndecesCurrParent.add(index);
        }
        System.out.println(c1.getLength());

        // now, we fill C1 with P1,
        for (int i = 0; i < p1.getLength(); i++) {
            c1.getPackages().set(i, p1.getPackages().get(i));
        }
        // then replace the values in the order they were obtained in specialValues
        for (int i = 0; i < specialValues.size(); i++) {
            c1.getPackages().set(specialIndecesCurrParent.get(i), specialValues.get(i));
        }
    }

    public static void main (String[] args) {
        ArrayList<Integer> p1Packages = new ArrayList<>();
        ArrayList<Integer> p2Packages = new ArrayList<>();

        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(2);
        p1Packages.add(2);
        p1Packages.add(3);
        p1Packages.add(3);
        p1Packages.add(4);
        p1Packages.add(4);
        p1Packages.add(5);
        p2Packages.add(5);
        p2Packages.add(4);
        p2Packages.add(4);
        p2Packages.add(3);
        p2Packages.add(3);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(1);
        p2Packages.add(1);

        Chromosome p1 = new Chromosome(p1Packages);
        Chromosome p2 = new Chromosome(p2Packages);

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);

        Order2 order2 = new Order2(p1, p2, 9);

    }
}
