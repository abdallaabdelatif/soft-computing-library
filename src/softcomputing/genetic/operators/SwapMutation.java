package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import java.util.Random;

public class SwapMutation<T> implements MutationStrategy<T> {
    private final Random random = new Random();

    @Override
    public void mutate(Chromosome<T> chromosome, double mutationRate) {
        T[] genes = chromosome.getGenes();
        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                int j = random.nextInt(genes.length);
                T temp = genes[i];
                genes[i] = genes[j];
                genes[j] = temp;
            }
        }
        chromosome.setGenes(genes);
    }
}
