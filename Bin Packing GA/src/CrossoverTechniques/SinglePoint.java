package CrossoverTechniques;

import Main.Chromosome;

import java.util.ArrayList;
import java.util.Random;

public class SinglePoint {

    public Chromosome p1;
    public Chromosome p2;
    public Chromosome c1 = new Chromosome();
    public Chromosome c2 = new Chromosome();

    public final int NUMPACKAGES;

    public SinglePoint(Chromosome p1, Chromosome p2) {
        this.NUMPACKAGES = p1.getLength();
        this.p1 = p1;
        this.p2 = p2;

        for (int i = 0; i < NUMPACKAGES; i++) { // fill each child array with 0s to start (they will be replaced)
            c1.getPackages().add(0);
            c2.getPackages().add(0);
        }

//        doSinglePoint();

//        System.out.println("C1: " + c1);
//        System.out.println("C2: " + c2);
    }

    public void doSinglePoint() {
        int slicePoint = new Random().nextInt(NUMPACKAGES);
//        System.out.println(slicePoint);

        for (int i = 0; i < slicePoint; i++) {
            c1.getPackages().set(i, p1.getPackages().get(i));
        }
        for (int i = 0; i < slicePoint; i++) {
            c2.getPackages().set(i, p2.getPackages().get(i));
        }

        for (int i = slicePoint; i < NUMPACKAGES; i++) {
            int parentPackage = p2.getPackages().get(i);
            c1.getPackages().set(i, parentPackage);
        }
        for (int i = slicePoint; i < NUMPACKAGES; i++) {
            c2.getPackages().set(i, p1.getPackages().get(i));
        }
    }

    public static void main (String[] args) {
        ArrayList<Integer> p1Packages = new ArrayList<>();
        ArrayList<Integer> p2Packages = new ArrayList<>();

        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(1);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(2);

        Chromosome p1 = new Chromosome(p1Packages);
        Chromosome p2 = new Chromosome(p2Packages);

//        System.out.println("P1: " + p1);
//        System.out.println("P2: " + p2);

        SinglePoint sp = new SinglePoint(p1, p2);

    }
}
