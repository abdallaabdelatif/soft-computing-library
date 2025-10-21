package softcomputing.genetic.operators;

import java.util.Random;
import softcomputing.genetic.chromosome.Chromosome;

public class InsertMutation<T> implements MutationStrategy<T> {
    private final Random random = new Random();

    @Override
    public void mutate(Chromosome<T> chromosome, double mutationRate) {
        T[] genes = chromosome.getGenes();
        if (random.nextDouble() < mutationRate) {
            int pos1 = random.nextInt(genes.length);
            int pos2 = random.nextInt(genes.length);

            if (pos1 != pos2) {
                T value = genes[pos2];

                if (pos2 > pos1) {
                    System.arraycopy(genes, pos1 + 1, genes, pos1 + 2, pos2 - pos1 - 1);
                    genes[pos1 + 1] = value;
                } else {
                    System.arraycopy(genes, pos2, genes, pos2 + 1, pos1 - pos2);
                    genes[pos2] = value;
                }
            }
        }
        chromosome.setGenes(genes);
    }
}
