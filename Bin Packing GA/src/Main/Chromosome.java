package Main;

import java.util.ArrayList;

public class Chromosome {
    int fitness;
    ArrayList<Integer> packages = new ArrayList<Integer>();


    public Chromosome(ArrayList<Integer> packages) {
        this.packages = packages;
    }

    public Chromosome() {

    }

    public int getLength() {
        return packages.size();
    }

    public void addBin(Integer p) {
        packages.add(p);
    }

    public void setFitness(int newFitness) {
        this.fitness = newFitness;
    }


    public String toString() {
        String returnStr = "";
        for(Integer p : packages) {
            returnStr += p + ", ";
        }
        returnStr += "\n Fitness: " + fitness;
        return returnStr;
    }


    public ArrayList<Integer> getPackages() {
        return this.packages;
    }



}
