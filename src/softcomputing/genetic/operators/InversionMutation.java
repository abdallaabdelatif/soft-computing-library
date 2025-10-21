package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import java.util.Random;

public class InversionMutation<T> implements MutationStrategy<T> {
    private final Random random = new Random();

    @Override
    public void mutate(Chromosome<T> chromosome, double mutationRate) {
        T[] genes = chromosome.getGenes();

        if (random.nextDouble() < mutationRate) {
            int start = random.nextInt(genes.length);
            int end = random.nextInt(genes.length);

            if (start > end){
                int temp = start;
                start = end;
                end = temp;
            }

            while (start < end) {
                T temp = genes[start];
                genes[start] = genes[end];
                genes[end] = temp;
                start++;
                end--;
            }
        }

        chromosome.setGenes(genes);
    }
}
