package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import java.util.Random;

public class BitFlipMutation implements MutationStrategy<Integer> {
    private final Random random = new Random();

    @Override
    public void mutate(Chromosome<Integer> chromosome, double mutationRate) {
        Integer[] genes = chromosome.getGenes();
        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                genes[i] = 1 - genes[i];
            }
        }
        chromosome.setGenes(genes);
    }
}