package CrossoverTechniques;
import Main.Chromosome;

import java.util.ArrayList;
import java.util.Random;


public class Order1 {
    public Chromosome p1;
    public Chromosome p2;
    public Chromosome c1 = new Chromosome();
    public Chromosome c2 = new Chromosome();
    public final int POPULATIONSIZE;


    public Order1(Chromosome p1, Chromosome p2, int POPULATIONSIZE) {
        this.p1 = p1;
        this.p2 = p2;
        this.POPULATIONSIZE = POPULATIONSIZE;
        for (int i = 0; i < POPULATIONSIZE; i++) {
            c1.getPackages().add(0);
            c2.getPackages().add(0);
        }

        doOrder1();
    }

    public void doOrder1() { // PROBLEM: order1 takes duplicates into account, but I'm allowed to have duplicates here. I just need to ensure the right number of each weight is added to the children.
//        int slice1 = new Random().nextInt(POPULATIONSIZE);
//        int slice2 = new Random().nextInt(POPULATIONSIZE);
        int slice1 = 2;
        int slice2 = 5;

        // the following loop creates a dictionary of keys and values between the slice points
        if (slice1 != slice2) {
            if (slice1 > slice2) { // if slices are backwards, swap them to iterate from lower to higher slice.
                int temp = slice1;
                slice1 = slice2;
                slice2 = temp;
            }
            for (int i = slice1; i < slice2; i++) { // inherit points between slices from each respective parent
                c1.getPackages().set(i, p1.getPackages().get(i));
                c2.getPackages().set(i, p2.getPackages().get(i));
            }

            // the following portion iterates over P1 (starting from second slice point), adding values not already in c2 to c2.
            {
                int p1AtIndex;
                int addIndex = slice2;
                for (int i = slice2; i < p1.getLength(); i++) {
                    p1AtIndex = p1.getPackages().get(i);
                    if (!c2.getPackages().contains(p1AtIndex)) { //if item at index is not already in c2
                        c2.getPackages().set(addIndex, p1AtIndex);
                        addIndex = ((addIndex + 1) % p1.getLength());
                    }

                }
                for (int i = 0; i < slice2; i++) {
                    p1AtIndex = p1.getPackages().get(i);
                    if (!c2.getPackages().contains(p1AtIndex)) { //if item at index is not already in c2
                        c2.getPackages().set(addIndex, p1AtIndex);
                        addIndex = ((addIndex + 1) % p1.getLength());
                    }

                }
            }
            // the following portion iterates over P2 (starting from second slice point), adding values not already in c1 to c1.
            {
                int p2AtIndex;
                int addIndex = slice2;
                for (int i = slice2; i < p2.getLength(); i++) {
                    p2AtIndex = p2.getPackages().get(i);
                    if (!c1.getPackages().contains(p2AtIndex)) { //if item at index is not already in c1
                        c1.getPackages().set(i, p2AtIndex);
                        addIndex = ((addIndex + 1) % p2.getLength());
                    }

                }
                for (int i = 0; i < slice2; i++) {
                    p2AtIndex = p2.getPackages().get(i);
                    if (!c1.getPackages().contains(p2AtIndex)) { //if item at index is not already in c1
                        c1.getPackages().set(i, p2AtIndex);
                        addIndex = ((addIndex + 1) % p2.getLength());
                    }
                }

                System.out.println("p1: " + p1);
                System.out.println("p2: " + p2);
                System.out.println("c1: " + c1);
                System.out.println("c2: " + c2);
                System.out.println("Slice 1 = " + slice1);
                System.out.println("Slice 2 = " + slice2);

            }


        }
        else { // if slice1 == slice2
            c1 = p1;
            c2 = p2;
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

        Order1 order1 = new Order1(p1, p2, 9);

    }
}