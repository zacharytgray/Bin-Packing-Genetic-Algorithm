package CrossoverTechniques;

import Main.Chromosome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SinglePoint {

    public Chromosome p1;
    public Chromosome p2;
    public Chromosome c1 = new Chromosome();
    public Chromosome c2 = new Chromosome();

    public int NUMPACKAGES;

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


        for (int i = 0; i < slicePoint; i++) {  // put first half of p1 in c1
            c1.getPackages().set(i, p1.getPackages().get(i));
        }
        for (int i = 0; i < slicePoint; i++) { // put first half of p2 in c2
            c2.getPackages().set(i, p2.getPackages().get(i));
        }


        for (int i = slicePoint; i < NUMPACKAGES; i++) { // put second half of p2 in c1
            int parentPackage = p2.getPackages().get(i);
            c1.getPackages().set(i, parentPackage);
        }
        for (int i = slicePoint; i < NUMPACKAGES; i++) { // put second half of p1 in c2
            c2.getPackages().set(i, p1.getPackages().get(i));
        }

        // now, we check for infeasibles

        Map<Integer, Integer> p1weightMap = createWeightMap(p1); // map of weight, count pairs that count how many of a package weight are in a chromosome
        Map<Integer, Integer> p2weightMap = createWeightMap(p2);
        Map<Integer, Integer> c1weightMap = createWeightMap(c1);
        Map<Integer, Integer> c2weightMap = createWeightMap(c2);

        Map<Integer, Integer> offsetMap1 = getOffsetMap(p1weightMap, c1weightMap);
        Map<Integer, Integer> offsetMap2 = getOffsetMap(p2weightMap, c2weightMap);


        System.out.println("p1 weight map: " + p1weightMap);
//        System.out.println("p2 weight map: " + p2weightMap);
        System.out.println("c1 weight map: " + c1weightMap);
//        System.out.println("c2 weight map: " + c2weightMap);

        System.out.println("offset map 1: " + offsetMap1);
        System.out.println("offset map 2: " + offsetMap2);


    }

    public Map<Integer, Integer> getOffsetMap(Map<Integer, Integer> parentMap, Map<Integer, Integer> childMap) {
        // Iterate through each child, subtract weights from parent weight maps as you go
        // Once we're done, we'll have a map of how many number of each weight we are "away" for each weight.

        Map<Integer, Integer> offsetMap = new HashMap<>(); //"offset" map between parent and child

        // offset map is essentially the parent map minus child map
        for (Integer weight : parentMap.keySet()) {
            if (childMap.containsKey(weight)) { // if the child map contains the weight in question, subtract p-c and add to offset map
                int difference = parentMap.get(weight) - childMap.get(weight);
                offsetMap.put(weight, difference);
            }
            else { // if child map does not contain the weight in question, add all n weights of this weight from parent map to the offset map
                offsetMap.put(weight, parentMap.get(weight));
            }
        }

        return offsetMap;

    }

    public Map<Integer, Integer> createWeightMap(Chromosome c) {
        Map<Integer, Integer> weightMap = new HashMap<>();
        int count;
        for (int i = 0; i < c.getLength(); i++) {
            if (!weightMap.containsKey(c.getPackages().get(i))) {
                //if weight does not exist already, set count to 1.
                count = 1;
            } else {
                count = weightMap.get(c.getPackages().get(i)) + 1;
            }
            weightMap.put(c.getPackages().get(i), count);
        }
        return weightMap;
    }

    public static void main (String[] args) {
        ArrayList<Integer> p1Packages = new ArrayList<>();
        ArrayList<Integer> p2Packages = new ArrayList<>();

        p1Packages.add(1); //five 1s, four 2s
        p1Packages.add(1);
        p1Packages.add(1);
        p1Packages.add(2);
        p1Packages.add(2);
        p1Packages.add(2);
        p1Packages.add(3);
        p1Packages.add(3);
        p1Packages.add(3);

        p2Packages.add(3); // five 1s, four 2s
        p2Packages.add(3);
        p2Packages.add(3);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(2);
        p2Packages.add(1);
        p2Packages.add(1);
        p2Packages.add(1);

        Chromosome p1 = new Chromosome(p1Packages);
        Chromosome p2 = new Chromosome(p2Packages);

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);

        SinglePoint sp = new SinglePoint(p1, p2);

    }
}
