package Main;

import CrossoverTechniques.SinglePoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Generation {
    ArrayList<Chromosome> population = new ArrayList<Chromosome>();
    ArrayList<Chromosome> parentPool = new ArrayList<Chromosome>();
    ArrayList<Chromosome> childPool = new ArrayList<Chromosome>();

    ArrayList<Integer> packages;
    Bin testBin = new Bin();

    public final int POPULATIONSIZE = 100;

    public Generation(ArrayList<Integer> allPackages) {
        this.packages = allPackages;
        initializePopulation(); // Creates population pool from data set
        doTournament(); // parent pool created via tournament selection
        doSinglePoint();
        for(Chromosome c: childPool) {
            System.out.println(c.fitness);
        }
//        System.out.println(childPool.size());
    }

    public void initializePopulation() {
        for (int i = 0; i < POPULATIONSIZE; i++) {
            ArrayList<Integer> shuffledPackages = new ArrayList<>(packages);
            Collections.shuffle(shuffledPackages);

            Chromosome c = new Chromosome(shuffledPackages);
            ArrayList<Bin> bins = bestFit(shuffledPackages);
            c.setFitness(bins.size()); // set fitness to number of bins after best fit
            population.add(c);
        }



    }

    public ArrayList<Bin> bestFit(ArrayList<Integer> packages) {
        ArrayList<Bin> bins = new ArrayList<Bin>();
        int binFreeSpace;
        int minFreeSpaceIndex;
        int minFreeSpaceSoFar;

        for (Integer p: packages) {

            // The following loop finds the index of the bin with the tightest spot for package p to fit
            minFreeSpaceIndex = -1;
            minFreeSpaceSoFar = testBin.CAPACITY; // bin capacity is the maximum amount of free space possible
            for (int i = 0; i < bins.size(); i++) {
                binFreeSpace = bins.get(i).getFreeSpace();
                if (binFreeSpace >= p && binFreeSpace < minFreeSpaceSoFar) { // if we found the tightest spot for package to fit
                    // In the second half of that condition, we ensure the lower numbered bin would win in the event
                    // of a tie because of the < sign, rather than <=.
                    minFreeSpaceIndex = i;
                    minFreeSpaceSoFar = binFreeSpace;
                }
            }
            if (minFreeSpaceIndex == -1) {
                bins.add(new Bin());
                bins.get(bins.size() - 1).addPackage(p); //add new bin, put package in that bin
            }
            else {
                bins.get(minFreeSpaceIndex).addPackage(p); // add package p at that index
            }

        }

        return bins;
    }

    public void doTournament() { // performs Main.Tournament selection
        int randomIndex;
        Tournament t = new Tournament();

        for (int i = 0; i < POPULATIONSIZE; i++) {
            randomIndex = new Random().nextInt(POPULATIONSIZE);
            Chromosome c1 = population.get(randomIndex);
            randomIndex = new Random().nextInt(POPULATIONSIZE);
            Chromosome c2 = population.get(randomIndex);

            Chromosome winner = t.doTournament(c1, c2);
            parentPool.add(winner);
        }
    }

    public void doSinglePoint() {
        int randomIndex;
        for (int i = 0; i < POPULATIONSIZE; i++) {
            randomIndex = new Random().nextInt(POPULATIONSIZE);
            Chromosome p1 = parentPool.get(randomIndex);
            randomIndex = new Random().nextInt(POPULATIONSIZE);
            Chromosome p2 = parentPool.get(randomIndex);

            SinglePoint sp = new SinglePoint(p1, p2);
            sp.doSinglePoint();
            childPool.add(sp.c1);
            childPool.add(sp.c2);

        }


        // update fitnesses for each chromosome with bestFit
        for (int i = 0; i < childPool.size(); i++) {
            int fitness = bestFit(childPool.get(i).getPackages()).size();
            childPool.get(i).setFitness(fitness);
        }
    }



}


//    public Main.Generation(ArrayList<Integer> allPackages) { //performs first fit algorithm from given weights to get a starting chromosome
//        boolean allPacked = false; // becomes true when all packages are put into bins
//        int lastPackageIndex = allPackages.size() - 1;
//        int startWindow = 0;
//        int endWindow = 1;
//
//
//        Main.Chromosome c = new Main.Chromosome();
//        int count = 0;
//        while(!allPacked) {
//            int sum = 0;
//            for (int i = startWindow; i < endWindow; i++) {
////                if (i <= lastPackageIndex) {
////                }
//                sum += allPackages.get(i);
//
//            }
//            if (sum <= 10) { // if you are not at capacity
//                if (endWindow >= lastPackageIndex) {
//                    allPacked = true;
//                    System.out.println("----- NEW BIN -----");
//                    Main.Bin b = new Main.Bin();
//                    for (int weightIndex = startWindow; weightIndex <= lastPackageIndex; weightIndex++) {
//                        b.addPackage(new Package(allPackages.get(weightIndex)));
//                    }
//                    System.out.println(b);
//                    c.addBin(b);
//                }
//                endWindow++;
//
//            }
//            else { // if you are at capacity
//                System.out.println("----- NEW BIN -----");
//                Main.Bin b = new Main.Bin();
//                for (int weightIndex = startWindow; weightIndex < endWindow - 1; weightIndex++) {
//                    b.addPackage(new Package(allPackages.get(weightIndex)));
//                }
//                startWindow = endWindow - 1;
//                endWindow = startWindow;
//                System.out.println(b);
//                c.addBin(b);
//            }
//
//        }
//        System.out.println("There were " + c.getLength() + " bins used");
//
//    }