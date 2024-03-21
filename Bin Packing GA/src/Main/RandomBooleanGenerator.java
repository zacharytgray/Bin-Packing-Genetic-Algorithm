package Main;

import java.util.Random;

public class RandomBooleanGenerator {
    private int probabilityPercentage;

    public RandomBooleanGenerator(int probabilityPercentage) {
        if (probabilityPercentage < 0 || probabilityPercentage > 100) {
            throw new IllegalArgumentException("Probability percentage must be between 0 and 100 (inclusive).");
        }
        this.probabilityPercentage = probabilityPercentage;
    }

    public boolean generateBoolean() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1; // Generate random number between 1 and 100

        return randomNumber <= probabilityPercentage;
    }

//    public static void main(String[] args) {
//        int probability = 75;
//        Main.RandomBooleanGenerator generator = new Main.RandomBooleanGenerator(probability);
//
//        // Test the generator
//        for (int i = 0; i < 10; i++) {
//            boolean result = generator.generateBoolean();
//            System.out.println("Random result #" + (i + 1) + ": " + result);
//        }
//    }
}
