package Main;

public class Tournament {
    public Chromosome c1;
    public Chromosome c2;

    public final int ALPHA = 75;

    public Tournament() {
        this.c1 = c1;
        this.c2 = c2;

    }


    public Chromosome doTournament(Chromosome c1, Chromosome c2) {
        int fitness1 = c1.fitness;
        int fitness2 = c2.fitness;
        RandomBooleanGenerator generator = new RandomBooleanGenerator(ALPHA);
        boolean returnCondition = generator.generateBoolean();

        if (fitness1 <= fitness2) { //if c1's fitness is <= c2's, there is a 75% chance c1 lives on
            if (returnCondition) {
                return c1;
            }
            return c2;
        }
        else { // If c2 > c1, 75% chance c2 lives
            if (returnCondition) {
                return c2;
            }
            return c1;
        }
    }




}
