package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.BinaryChromosome;
import softcomputing.genetic.chromosome.Chromosome;
import java.util.Random;

public class BitFlipMutation implements MutationStrategy<Integer> {
    private final Random random = new Random();

    @Override
    public void mutate(Chromosome<Integer> chromosome, double mutationRate) {
        Integer[] genes = chromosome.getGenes();

        Random random = new Random();
        int minValue = chromosome.getMin();
        int maxValue = chromosome.getMax();
        int bits = Integer.SIZE - Integer.numberOfLeadingZeros(maxValue) ;
        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                int bitPosition = random.nextInt(bits);
                int mutatedGene = genes[i] ^ (1 << bitPosition);
                if (mutatedGene >= minValue && mutatedGene <= maxValue) {
                    genes[i] = mutatedGene;
                } else {
                    genes[i] = random.nextInt(maxValue - minValue + 1) + minValue;
                }
            }
        }

        chromosome.setGenes(genes);
    }

}