import java.lang.reflect.Array;
import java.util.ArrayList;

public class Generation {
    ArrayList<Chromosome> population = new ArrayList<Chromosome>();


    public Generation(ArrayList<Integer> allPackages) { // Sorts packages into bins using Best Fit algorithm
        ArrayList<Bin> bins = new ArrayList<Bin>();
        int count = 0;
        for (Integer p: allPackages) {

            // The following loop finds the index of the bin with the tightest spot for package p to fit
            int minFreeSpaceIndex = -1;
            int minFreeSpaceSoFar = 10; // 10 = bin capacity, which is the maximum amount of free space possible
            for (int i = 0; i < bins.size(); i++) {
                int binFreeSpace = bins.get(i).getFreeSpace();
                if (binFreeSpace >= p && binFreeSpace < minFreeSpaceSoFar) { // if we found the tightest spot for package to fit
                    minFreeSpaceIndex = i;
                    minFreeSpaceSoFar = binFreeSpace;
                }
            }
            if (minFreeSpaceIndex == -1) {
                bins.add(new Bin());
                count++;
                bins.get(bins.size() - 1).addPackage(new Package(p)); //add new bin, put package in that bin
            }
            else {
                bins.get(minFreeSpaceIndex).addPackage(new Package(p)); // add package p at that index
            }

        }

        System.out.println("Solved using " + count + " bins");
        for(Bin b : bins) {
            System.out.println("NEW BIN: \n" + b);
        }


    }
//    public Generation(ArrayList<Integer> allPackages) { //performs first fit algorithm from given weights to get a starting chromosome
//        boolean allPacked = false; // becomes true when all packages are put into bins
//        int lastPackageIndex = allPackages.size() - 1;
//        int startWindow = 0;
//        int endWindow = 1;
//
//
//        Chromosome c = new Chromosome();
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
//                    Bin b = new Bin();
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
//                Bin b = new Bin();
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



    public void bestFit(Chromosome c) {
        ArrayList<Package> packages = c.getPackages();
        ArrayList<Bin> bins = new ArrayList<Bin>();
        bins.add(new Bin());


        for (Package p: packages) {

            // The following loop finds the index of the bin with the tightest spot for package p to fit
            int minFreeSpaceIndex = -1;
            int maxFreeSpaceSoFar = 10; // 10 = bin capacity, which is the maximum amount of free space possible
            for (int i = 0; i < bins.size(); i++) {
                int binFreeSpace = bins.get(i).getFreeSpace();
                if (binFreeSpace > p.weight && binFreeSpace < maxFreeSpaceSoFar) { // if we found the tightest spot for package to fit
                    minFreeSpaceIndex = i;
                    maxFreeSpaceSoFar = binFreeSpace;
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
    }
}
