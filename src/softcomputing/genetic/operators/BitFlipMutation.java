package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import java.util.Random;

public class BitFlipMutation implements MutationStrategy<Boolean> {
    private final Random random = new Random();

    @Override
    public void mutate(Chromosome<Boolean> chromosome, double mutationRate) {
        Boolean[] genes = chromosome.getGenes();
        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                genes[i] = !genes[i];
            }
        }
        chromosome.setGenes(genes);
    }
}