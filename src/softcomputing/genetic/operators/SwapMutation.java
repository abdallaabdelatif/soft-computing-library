package softcomputing.genetic.operators;

import java.util.Arrays;
import java.util.Random;
import softcomputing.genetic.chromosome.Chromosome;

public class SwapMutation<T> implements MutationStrategy<T> {
    private final Random random = new Random();

    @Override
    public void mutate(Chromosome<T> chromosome, double mutationRate) {
        T[] genes = chromosome.getGenes();

        System.out.println("Before mutation: " + Arrays.toString(genes));

        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                int j = random.nextInt(genes.length);
                T temp = genes[i];
                genes[i] = genes[j];
                genes[j] = temp;
            }
        }

        System.out.println("After mutation:  " + Arrays.toString(genes));

        chromosome.setGenes(genes);
    }
}
